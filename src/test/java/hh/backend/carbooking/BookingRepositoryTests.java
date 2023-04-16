package hh.backend.carbooking;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.backend.carbooking.domain.Booking;
import hh.backend.carbooking.domain.BookingRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookingRepositoryTests {
    @Autowired
    private BookingRepository bookingRepository;

    // Test to add booking
    @Test
    public void addBookingTest() {
        String startDate = "2023-06-10";
        String endDate = "2023-06-14";
        Date date1 = Date.valueOf(startDate);
        Date date2 = Date.valueOf(endDate);
        Booking booking = new Booking(date1, date2, null, null);
        bookingRepository.save(booking);
        assertThat(booking.getBookingId()).isNotNull();
    }

    // Test to get booking
    @Test
    public void getBookingTest() {
        String startDate = "2023-06-10";
        String endDate = "2023-06-14";
        Date date1 = Date.valueOf(startDate);
        Date date2 = Date.valueOf(endDate);
        Booking booking = new Booking(date1, date2, null, null);
        bookingRepository.save(booking);
        Booking check = bookingRepository.findByStartDate(date1);
        assertThat(check.getEndDate()).isEqualTo(date2);
    }

    // Test to delete booking
    @Test
    public void deleteBookingTest() {
        String startDate = "2023-06-10";
        String endDate = "2023-06-14";
        Date date1 = Date.valueOf(startDate);
        Date date2 = Date.valueOf(endDate);
        Booking booking = new Booking(date1, date2, null, null);
        bookingRepository.save(booking);
        Booking before = bookingRepository.findByStartDate(date1);
        bookingRepository.deleteById(before.getBookingId());
        Booking after = bookingRepository.findByStartDate(date1);
        assertThat(after).isNull();
    }
}
