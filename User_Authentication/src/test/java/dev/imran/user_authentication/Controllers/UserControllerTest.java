package dev.imran.user_authentication.Controllers;
import dev.imran.user_authentication.Service.UserService;
import dev.imran.user_authentication.Controller.UserController;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @InjectMocks
    private UserService userService;

    @Test
    void validateToken() {


    }
}