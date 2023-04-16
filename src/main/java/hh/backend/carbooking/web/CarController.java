package hh.backend.carbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.carbooking.domain.BookingRepository;
import hh.backend.carbooking.domain.Car;
import hh.backend.carbooking.domain.CarRepository;
import hh.backend.carbooking.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class CarController {

    @Autowired
    CarRepository cRepository;
    @Autowired
    BookingRepository bRepository;
    @Autowired
    UserRepository uRepository;

    private String currentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return currentUserName;
        }
        return null;
    }

    // List of all cars
    @GetMapping("/carlist")
    public String carList(Model model) {
        model.addAttribute("user", uRepository.findByUsername(currentUserName()));
        model.addAttribute("cars", cRepository.findAll());
        return "car/carlist"; // carlist.html
    }

    // Add a new car
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addcar")
    public String addCar(Model model) {
        model.addAttribute("car", new Car());
        return "car/addcar"; // addcar.html
    }

    // Save a car
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/savecar")
    public String saveCar(@Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "car/addcar";
        } else {
            cRepository.save(car);
            return "redirect:/carlist"; // carlist.html
        }
    }

    // Save a car
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveeditedcar")
    public String saveEditedCar(@Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/editcar/" + car.getCarId();
        } else {
            cRepository.save(car);
            return "redirect:/carlist"; // carlist.html
        }
    }

    // Edit one car by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editcar/{id}")
    public String editCar(@PathVariable("id") Long carId, Model model) {
        model.addAttribute("car", cRepository.findById(carId));
        return "car/editcar"; // editcar.html
    }

    // Delete one car by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletecar/{id}")
    public String deleteCar(@PathVariable("id") Long carId) {
        cRepository.deleteById(carId);
        return "redirect:/carlist"; // carlist.html
    }
}
