package hh.backend.carbooking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CarRepositoryTests {
    @Autowired
    private CarRepository carRepository;

    // Test to add car
    @Test
    public void addCarTest() {
        Car car = new Car("test", "test", 1993);
        carRepository.save(car);
        assertThat(car.getCarId()).isNotNull();
    }

    // Test to get car
    @Test
    public void getCarTest() {
        Car car = carRepository.findByModel("Camry");
        assertThat(car.getModel()).isEqualTo("Camry");
    }

    // Test to delete car
    @Test
    public void deleteCarTest() {
        Car car = carRepository.findByModel("Camry");
        carRepository.deleteById(car.getCarId());
        Car after = carRepository.findByModel("Camry");
        assertThat(after).isNull();
    }
}
