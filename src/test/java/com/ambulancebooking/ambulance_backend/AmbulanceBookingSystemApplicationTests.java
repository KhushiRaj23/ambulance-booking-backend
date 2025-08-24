package com.ambulancebooking.ambulance_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
    "spring.jpa.hibernate.ddl-auto=create-drop",
    "spring.jwt.secret=test-jwt-secret-key-for-testing-purposes-only",
    "frontend.url=http://localhost:3000",
    "google.maps.api.key=test-api-key"
})
class AmbulanceBookingSystemApplicationTests {

	@Test
	void contextLoads() {
	}

}
