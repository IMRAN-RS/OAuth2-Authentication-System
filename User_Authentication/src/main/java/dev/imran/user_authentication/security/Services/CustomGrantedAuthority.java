package dev.imran.user_authentication.security.Services;

import dev.imran.user_authentication.Models.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {

    public String role;

    public CustomGrantedAuthority() {
    }

    public CustomGrantedAuthority(Role role) {
        this.role = role.getName();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
