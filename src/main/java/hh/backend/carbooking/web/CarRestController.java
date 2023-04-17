package hh.backend.carbooking.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;

@RestController
public class CarRestController {

    @Autowired
    CarRepository cRepository;

    // All cars
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/cars")
    @ResponseBody
    List<Car> carListRest() {
        return (List<Car>) cRepository.findAll();
    }

    // One car by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/cars/{id}")
    @ResponseBody
    Optional<Car> findCarByIdRest(@PathVariable("id") Long carId) {
        return cRepository.findById(carId);
    }

    // New car
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/cars")
    Car newCar(@RequestBody Car newCar) {
        return cRepository.save(newCar);
    }

    // Edit car
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/cars/{id}")
    Car replaceCar(@RequestBody Car newCar, @PathVariable("id") Long carId) {
        return cRepository.findById(carId)
                .map(car -> {
                    car.setBrand(newCar.getBrand());
                    car.setModel(newCar.getModel());
                    return cRepository.save(car);
                })
                .orElseGet(() -> {
                    newCar.setCarId(carId);
                    return cRepository.save(newCar);
                });
    }

    // Delete car
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable("id") Long carId) {
        cRepository.deleteById(carId);
    }
}
