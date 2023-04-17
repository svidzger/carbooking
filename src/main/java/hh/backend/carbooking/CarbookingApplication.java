package hh.backend.carbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;
import hh.backend.carbooking.domain.User;
import hh.backend.carbooking.domain.UserRepository;

@SpringBootApplication
public class CarbookingApplication {

	private static final Logger log = LoggerFactory.getLogger(CarbookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarbookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner carbookingDemo(CarRepository cRepository, UserRepository uRepository) {
		return (args) -> {

			log.info("saving test cars");

			// Test cars for CarController and car realted thymeleaf html files
			cRepository.save(new Car("Toyota", "Camry", 1993));
			cRepository.save(new Car("Subaru", "Impreza", 1994));
			cRepository.save(new Car("Toyota", "Corolla", 1993));
			cRepository.save(new Car("Audi", "A4", 1996));
			cRepository.save(new Car("Tesla", "Model S", 2012));

			log.info("fetch all cars");
			for (Car car : cRepository.findAll()) {
				log.info(car.toString());
			}

			log.info("saving test user");

			// Create test users
			User user1 = new User("user", "$2a$10$9GVY78j6TXCbePZtjLPVQOopBLQw1z4Isr4RR01IDbnxDJWknZV4C",
					"user@email.com", "USER");
			User user2 = new User("admin", "$2a$10$zRHb1X18PiVGVne6ZKaD5Oqu3n7AWka43bMvYXAUom84jrayRE0dO",
					"admin@email.com", "ADMIN");

			uRepository.save(user1);
			uRepository.save(user2);

			log.info("fetch all users");
			for (User user : uRepository.findAll()) {
				log.info(user.toString());
			}

		};
	}
}