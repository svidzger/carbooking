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
import org.springframework.web.bind.annotation.RestController;

import hh.backend.carbooking.domain.Booking;
import hh.backend.carbooking.domain.BookingRepository;

@RestController
public class BookingRestController {

    @Autowired
    BookingRepository bRepository;

    // All booking
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/bookings")
    List<Booking> carListRest() {
        return (List<Booking>) bRepository.findAll();
    }

    // One booking by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("bookings/{id}")
    Optional<Booking> findBookingByIdRest(@PathVariable("id") Long bookingId) {
        return bRepository.findById(bookingId);
    }

    // New booking
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/bookings")
    Booking newBooking(@RequestBody Booking newBooking) {
        return bRepository.save(newBooking);
    }

    // Edit booking
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/bookings/{id}")
    Booking replaceBooking(@RequestBody Booking newBooking, @PathVariable("id") Long bookingId) {
        return bRepository.findById(bookingId)
                .map(booking -> {
                    booking.setStartDate(newBooking.getStartDate());
                    booking.setEndDate(newBooking.getEndDate());
                    return bRepository.save(booking);
                })
                .orElseGet(() -> {
                    newBooking.setBookingId(bookingId);
                    return bRepository.save(newBooking);
                });
    }

    // Delete booking
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/bookings/{id}")
    void deleteBooking(@PathVariable("id") Long bookingId) {
        bRepository.deleteById(bookingId);
    }
}
