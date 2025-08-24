-- Ambulance Booking System Database Initialization Script

-- Create database if it doesn't exist (this line might not be needed as POSTGRES_DB handles it)
-- CREATE DATABASE IF NOT EXISTS ambulance_booking;

-- Use the database
\c ambulance_booking;

-- Grant necessary permissions
GRANT ALL PRIVILEGES ON DATABASE ambulance_booking TO postgres;

-- Create extension for UUID generation if needed
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Insert sample data after tables are created by Hibernate

-- Note: The following INSERT statements will only work after the application
-- has started and created the tables. You might want to move these to a 
-- separate data.sql file in src/main/resources for Spring Boot to handle.

-- Sample Hospitals (will be inserted after table creation)
-- INSERT INTO hospital (id, name, address, phone, latitude, longitude) VALUES
-- (1, 'City General Hospital', '123 Main St, Downtown', '+1-555-0101', 40.7128, -74.0060),
-- (2, 'Regional Medical Center', '456 Oak Ave, Midtown', '+1-555-0102', 40.7589, -73.9851),
-- (3, 'Emergency Care Hospital', '789 Pine Rd, Uptown', '+1-555-0103', 40.7831, -73.9712);

-- Sample Ambulances (will be inserted after table creation)
-- INSERT INTO ambulance (id, license_plate, driver_name, phone, status, current_latitude, current_longitude, hospital_id) VALUES
-- (1, 'AMB-001', 'John Smith', '+1-555-0201', 'AVAILABLE', 40.7128, -74.0060, 1),
-- (2, 'AMB-002', 'Jane Doe', '+1-555-0202', 'AVAILABLE', 40.7589, -73.9851, 2),
-- (3, 'AMB-003', 'Mike Johnson', '+1-555-0203', 'BUSY', 40.7831, -73.9712, 3);

-- Print completion message
DO $$
BEGIN
    RAISE NOTICE 'Database initialization completed successfully!';
END $$;
