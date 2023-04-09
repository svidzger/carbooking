package hh.backend.carbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.carbooking.domain.BookingRepository;
import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;

@Controller
public class CarController {

    @Autowired
    CarRepository cRepository;
    @Autowired
    BookingRepository bRepository;


    // List of all cars
    @GetMapping("/carlist")
    public String carList(Model model) {
        model.addAttribute("cars", cRepository.findAll());
        return "car/carlist"; // carlist.html
    }

    @GetMapping("/addcar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "car/addcar"; // addcar.html
    }

    // Save a car
    @PostMapping("/savecar")
    public String saveCar(Car car) {
        cRepository.save(car);
        return "redirect:/carlist"; // carlist.html
    }

    // Edit one car by id
    @GetMapping("/editcar/{id}")
    public String editCar(@PathVariable("id") Long carId, Model model) {
        model.addAttribute("car", cRepository.findById(carId));
        return "car/editcar"; // editcar.html
    }

    // Delete one car by id
    @GetMapping("/deletecar/{id}")
    public String deleteCar(@PathVariable("id") Long carId) {
        cRepository.deleteById(carId);
        return "redirect:/carlist"; // carlist.html
    }
}
