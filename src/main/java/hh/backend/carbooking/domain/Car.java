package hh.backend.carbooking.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;

    @NotEmpty(message = "Brand is required")
    @Column(name = "brand")
    private String brand;

    @NotEmpty(message = "Model is required")
    @Column(name = "model")
    private String model;

    @Min(value = 1)
    @Column(name = "production_year")
    private Integer year;

    private Double price;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    public Car() {

    }

    public Car(String brand, String model, Integer year, Double price) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Car [carId=" + carId + ", brand=" + brand + ", model=" + model + ", year=" + year + ", price=" + price
                + ", bookings=" + bookings.size() + "]";
    }

}
