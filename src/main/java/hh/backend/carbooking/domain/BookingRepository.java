package hh.backend.carbooking.domain;

import java.sql.Date;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findByStartDate(Date startDate);
}
