package hh.backend.carbooking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.backend.carbooking.web.BookingController;
import hh.backend.carbooking.web.CarController;
import hh.backend.carbooking.web.UserController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CarbookingApplicationTests {

	@Autowired
	private CarController carController;

	@Autowired
	private UserController userController;

	@Autowired
	private BookingController bookingController;

	@Test
	void contextLoads() {
		assertThat(carController).isNotNull();
		assertThat(userController).isNotNull();
		assertThat(bookingController).isNotNull();
	}

}
