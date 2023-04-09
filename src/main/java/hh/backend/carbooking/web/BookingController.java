package hh.backend.carbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.carbooking.domain.Booking;
import hh.backend.carbooking.domain.BookingRepository;
import hh.backend.carbooking.domain.CarRepository;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bRepository;
    @Autowired
    CarRepository cRepository;

    // Add new booking
    @GetMapping("/addbooking/{id}")
    public String addBooking(@PathVariable("id") Long carId, Model model) {
        Booking booking = new Booking();
        booking.setCar(cRepository.findById(carId).get());
        model.addAttribute("booking", booking);
        model.addAttribute("car", cRepository.findById(carId).get());
        return "booking/addbooking"; // addbooking.html
    }

    // Save new booking
    @PostMapping("/savebooking")
    public String saveBooking(Booking booking) {
        bRepository.save(booking);
        return "redirect:/carlist"; // bookinglist.html
    }

}
