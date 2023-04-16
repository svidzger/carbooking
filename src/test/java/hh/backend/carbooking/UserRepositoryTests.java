package hh.backend.carbooking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.backend.carbooking.domain.User;
import hh.backend.carbooking.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    // Test to add user
    @Test
    public void addUserTest() {
        User user = new User("test user", "test password", "test@email.com", "test role");
        userRepository.save(user);
        assertThat(user.getId()).isNotNull();
    }

    // Test to get user
    @Test
    public void getUserTest() {
        User user = userRepository.findByUsername("user");
        assertThat(user.getUsername()).isEqualTo("user");
    }

    // Test to delete user
    @Test
    public void deleteUserTest() {
        User user = userRepository.findByUsername("user");
        userRepository.deleteById(user.getId());
        User after = userRepository.findByUsername("user");
        assertThat(after).isNull();
    }
}
