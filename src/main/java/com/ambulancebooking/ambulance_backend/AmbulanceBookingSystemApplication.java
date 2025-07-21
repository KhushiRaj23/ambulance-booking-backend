package com.ambulancebooking.ambulance_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import com.ambulancebooking.ambulance_backend.repository.UserRepository;
import com.ambulancebooking.ambulance_backend.model.User;
import com.ambulancebooking.ambulance_backend.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AmbulanceBookingSystemApplication {

	@Bean
	public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (userRepository.findByUsername("admin").isEmpty()) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(passwordEncoder.encode("admin123"));
				admin.setRole(Role.ADMIN);
				admin.setEmail("admin@example.com");
				admin.setPhone("0000000000"); // Set a default phone
				admin.setLatitude(0.0); // Set default values as needed
				admin.setLongitude(0.0);
				userRepository.save(admin);
				System.out.println("Default admin user created: username=admin, password=admin123");
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(AmbulanceBookingSystemApplication.class, args);
	}

}
