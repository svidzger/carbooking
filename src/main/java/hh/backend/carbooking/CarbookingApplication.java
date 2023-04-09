package hh.backend.carbooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;

@SpringBootApplication
public class CarbookingApplication {

	private static final Logger log = LoggerFactory.getLogger(CarbookingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CarbookingApplication.class, args);
	}

	@Bean
	public CommandLineRunner carbookingDemo(CarRepository cRepository) {
		return (args) -> {

			log.info("saving test cars");

			// Test cars for CarController and car realted thymeleaf html files
			cRepository.save(new Car("Toyota", "Camry", 1993, 24.99));
			cRepository.save(new Car("Subaru", "Impreza", 1994, 44.99));
			cRepository.save(new Car("Toyota", "Corolla", 1993, 14.99));
			cRepository.save(new Car("Audi", "A4", 1996, 24.99));
			cRepository.save(new Car("Tesla", "Model S", 2012, 59.99));

			log.info("fetch all cars");
			for (Car car : cRepository.findAll()) {
				log.info(car.toString());
			}
		};
	}
}