package dev.imran.user_authentication.Controller;

import dev.imran.user_authentication.Dtos.LoginRequestDto;
import dev.imran.user_authentication.Dtos.SignupRequestDto;
import dev.imran.user_authentication.Models.Token;
import dev.imran.user_authentication.Models.User;
import dev.imran.user_authentication.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signUp(@RequestBody SignupRequestDto signupRequestDto ) {

        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        String username = signupRequestDto.getUsername();

        return userService.signUp(email, password, username);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        String password = loginRequestDto.getPassword();

        return userService.login(email,password);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam("token") String token){

        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/validate/{token}")
    public boolean validateToken(@PathVariable("token") String token){
        return userService.validateToken(token);
    }

}
