package hh.backend.carbooking.domain;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;

    @Basic
    @NotNull(message = "Date is required")
    private java.sql.Date startDate;

    @Basic
    @NotNull(message = "Date is required")
    private java.sql.Date endDate;

    private Double bookingPrice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    public Booking() {

    }

    public Booking(Date startDate, Date endDate, Double bookingPrice, Car car, User user) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingPrice = bookingPrice;
        this.car = car;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate
                + ", bookingPrice=" + bookingPrice + ", car=" + car + "]";
    }

}
