package hh.backend.carbooking.domain;

import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    @Basic
    private java.sql.Date startDate;
    @Basic
    private java.sql.Date endDate;
    private Double bookingPrice;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    public Booking() {

    }

    public Booking(Date startDate, Date endDate, Double bookingPrice, Car car) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingPrice = bookingPrice;
        this.car = car;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public Double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(Double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate
                + ", bookingPrice=" + bookingPrice + ", car=" + car + "]";
    }

}