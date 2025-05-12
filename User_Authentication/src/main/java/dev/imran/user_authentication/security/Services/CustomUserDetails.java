package dev.imran.user_authentication.security.Services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import dev.imran.user_authentication.Models.Role;
import dev.imran.user_authentication.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
@JsonSerialize
@JsonDeserialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomUserDetails implements UserDetails {

    private String password;
    private String username;
    private List<CustomGrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails() {

    }

    public CustomUserDetails(User user) {
        this.password = user.getPassword();
        this.username = user.getEmail();


        for(Role role: user.getRoles()){
            authorities.add(new CustomGrantedAuthority(role));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
