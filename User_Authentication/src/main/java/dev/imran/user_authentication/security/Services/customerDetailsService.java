package dev.imran.user_authentication.security.Services;

import dev.imran.user_authentication.Models.User;
import dev.imran.user_authentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class customerDetailsService implements UserDetailsService {

    @Autowired
    private  UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> optionalUser = userRepository.findByEmail(username);

       if(optionalUser.isEmpty()) {
           throw new UsernameNotFoundException("User not found");
       }
        User user = optionalUser.get();
        return new CustomUserDetails(user);
    }


}
