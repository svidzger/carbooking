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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 4, max = 12, message = "Username must be between 4 and 12 characters")
    @NotEmpty(message = "Username is required")
    @Column(name = "username", unique = true)
    private String username;

    @NotEmpty(message = "Password is required")
    @Column(name = "password")
    private String passwordHash;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    private String role;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Booking> bookings;

    // Constructors
    public User() {

    }

    public User(
            @Size(min = 4, max = 12, message = "Username must be between 4 and 12 characters") @NotEmpty(message = "Username is required") String username,
            @NotEmpty(message = "Password is required") String passwordHash,
            @Email(message = "Email should be valid") @NotEmpty(message = "Email is required") String email,
            String role) {
        super();
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    // Getter and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

}
