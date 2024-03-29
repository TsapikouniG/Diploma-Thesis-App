package CS.UOI.diploma_thesis_application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import CS.UOI.diploma_thesis_application.model.User;


@SpringBootTest
class TestUserModel {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testGetAndSetId() {
        int expectedId = 1;
        user.setId(expectedId);
        int actualId = user.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    void testGetAndSetUsername() {
        String expectedUsername = "john.doe";
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();
        Assertions.assertEquals(expectedUsername, actualUsername);
    }

    @Test
    void testGetAndSetPassword() {
        String expectedPassword = "password";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();
        Assertions.assertEquals(expectedPassword, actualPassword);
    }

   

    @Test
    void testIsAccountNonExpired() {
        Assertions.assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        Assertions.assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        Assertions.assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        Assertions.assertTrue(user.isEnabled());
    }
}
