package dev.imran.user_authentication.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String email;
    private String password;
}
