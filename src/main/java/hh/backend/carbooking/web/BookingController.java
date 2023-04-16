package hh.backend.carbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.carbooking.domain.Booking;
import hh.backend.carbooking.domain.BookingRepository;
import hh.backend.carbooking.domain.CarRepository;
import hh.backend.carbooking.domain.UserRepository;

@Controller
public class BookingController {

    @Autowired
    BookingRepository bRepository;
    @Autowired
    CarRepository cRepository;
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

    // Add new booking
    @GetMapping("/addbooking/{id}")
    public String addBooking(@PathVariable("id") Long carId, Model model) {
        Booking booking = new Booking();
        booking.setCar(cRepository.findById(carId).get());
        booking.setUser(uRepository.findByUsername(currentUserName()));
        model.addAttribute("booking", booking);
        model.addAttribute("car", cRepository.findById(carId).get());
        return "booking/addbooking"; // addbooking.html
    }

    // Save new booking
    @PostMapping("/savebooking")
    public String saveBooking(Booking booking) {
        bRepository.save(booking);
        return "redirect:/userlist"; // bookinglist.html
    }

    // Delete/Cancel one booking by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletebooking/{id}")
    public String deleteBooking(@PathVariable("id") Long bookingId) {
        bRepository.deleteById(bookingId);
        return "redirect:/userlist"; // userlist.html
    }
}
