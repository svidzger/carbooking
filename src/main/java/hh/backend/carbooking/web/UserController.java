package hh.backend.carbooking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.carbooking.domain.User;
import hh.backend.carbooking.domain.UserRepository;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository uRepository;

    // User list
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userlist")
    public String userList(Model model, Authentication authentication) {
        model.addAttribute("users", uRepository.findAll());
        model.addAttribute("currentuser", uRepository.findByUsername(authentication.getName()));
        return "user/userlist";
    }

    // User page

    // Implement @PreAuthorize to restrict user profile access to only current user
    // own profile. Maybe current user == userprofile user id?
    @PreAuthorize("principal.username == authentication.name")
    @GetMapping("/userprofile/{id}")
    public String userProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", uRepository.findById(id).get());
        return "user/user";
    }

    // Add a new user
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/adduser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/adduser"; // adduser.html
    }

    // Save user
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveuser")
    public String saveUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/adduser";
        } else {
            String pswrd = user.getPasswordHash();
            user.setPasswordHash(passwordEncoder.encode(pswrd));
            uRepository.save(user);
            return "redirect:/userlist"; // userlist.html
        }
    }

    // Save edited user
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveediteduser")
    public String saveEditedUser(@Valid User user, BindingResult result, Authentication authentication) {
        User currentuser = uRepository.findByUsername(authentication.getName());
        if (result.hasErrors()) {
            return "redirect:/edituser/" + currentuser.getId();
        } else {
            String pswrd = user.getPasswordHash();
            user.setPasswordHash(passwordEncoder.encode(pswrd));
            uRepository.save(user);
            return "redirect:/userlist"; // userlist.html
        }
    }

    // New user registration
    @GetMapping("/register")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user/register"; // register.html
    }

    // Register new user
    @PostMapping("/registeruser")
    public String registerUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/register";
        } else {
            String pswrd = user.getPasswordHash();
            user.setPasswordHash(passwordEncoder.encode(pswrd));
            user.setRole("USER");
            uRepository.save(user);
            return "redirect:/carlist";
        }
    }

    // Edit one user by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edituser/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", uRepository.findById(id));
        return "user/edituser"; // edituser.html
    }

    // Delete one user by id
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        uRepository.deleteById(id);
        return "redirect:/userlist"; // userlist.html
    }
}
