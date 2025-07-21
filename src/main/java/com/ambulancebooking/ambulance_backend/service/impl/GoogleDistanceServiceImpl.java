package com.ambulancebooking.ambulance_backend.service.impl;

import com.ambulancebooking.ambulance_backend.service.GoogleDistanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class GoogleDistanceServiceImpl implements GoogleDistanceService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleDistanceServiceImpl.class);
    @Value("${google.maps.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        String url = String.format(
            "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%f,%f&destinations=%f,%f&key=%s",
            lat1, lon1, lat2, lon2, apiKey
        );
        logger.info("Calculating distance: origins=({}, {}), destinations=({}, {}), url={}", lat1, lon1, lat2, lon2, url);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.getForEntity(url, String.class);
            logger.warn("Google API raw response: {}", response.getBody());
            JSONObject json = new JSONObject(response.getBody());
            JSONArray rows = json.optJSONArray("rows");
            if (rows == null || rows.length() == 0) {
                logger.error("Google API response missing or empty 'rows' array: {}", response.getBody());
                // Fallback to Haversine
                final int R = 6371;
                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double haversine = R * c;
                logger.info("Haversine fallback distance: {} km", haversine);
                return haversine;
            }
            JSONArray elements = rows.getJSONObject(0).optJSONArray("elements");
            if (elements == null || elements.length() == 0) {
                logger.error("Google API response missing or empty 'elements' array: {}", response.getBody());
                // Fallback to Haversine
                final int R = 6371;
                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double haversine = R * c;
                logger.info("Haversine fallback distance: {} km", haversine);
                return haversine;
            }
            JSONObject element = elements.getJSONObject(0);
            if (element.getString("status").equals("OK")) {
                JSONObject distance = element.getJSONObject("distance");
                // Return distance in kilometers
                logger.info("Google API distance: {} meters", distance.getDouble("value"));
                return distance.getDouble("value") / 1000.0;
            } else {
                logger.warn("Google API element status not OK: {}", element.getString("status"));
                logger.warn("Google API response: {}", json.toString());
                // Fallback to Haversine if status is not OK
                try {
                    final int R = 6371;
                    double latDistance = Math.toRadians(lat2 - lat1);
                    double lonDistance = Math.toRadians(lon2 - lon1);
                    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                    double haversine = R * c;
                    logger.info("Haversine fallback distance: {} km", haversine);
                    return haversine;
                } catch (Exception ex) {
                    logger.error("Exception during Haversine calculation", ex);
                }
            }
        } catch (Exception e) {
            logger.error("Exception during Google API call or JSON parsing", e);
            if (response == null) {
                logger.error("Google API response is null (possible network error or invalid API key)");
            } else {
                logger.error("Google API response object: {}", response);
                logger.error("Google API raw response: {}", response.getBody());
            }
            // Fallback to Haversine if API fails
            try {
                final int R = 6371;
                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);
                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                        + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
                double haversine = R * c;
                logger.info("Haversine fallback distance: {} km", haversine);
                return haversine;
            } catch (Exception ex) {
                logger.error("Exception during Haversine calculation", ex);
            }
        }
        logger.error("Unable to calculate distance, returning Double.MAX_VALUE");
        return Double.MAX_VALUE; // If unable to calculate
    }
} 