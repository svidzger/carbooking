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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.backend.carbooking.domain.User;
import hh.backend.carbooking.domain.UserRepository;

@RestController
public class UserRestController {

    @Autowired
    UserRepository uRepository;

    // All uses
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    @ResponseBody
    List<User> userListRest() {
        return (List<User>) uRepository.findAll();
    }

    // User by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users/{id}")
    @ResponseBody
    Optional<User> findUserByIdRest(@PathVariable("id") Long id) {
        return uRepository.findById(id);
    }

    // New user
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return uRepository.save(newUser);
    }

    // Edit user
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable("id") Long id) {
        return uRepository.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());
                    user.setEmail(newUser.getEmail());
                    return uRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return uRepository.save(newUser);
                });
    }

    // Delete user
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") Long id) {
        uRepository.deleteById(id);
    }

}
