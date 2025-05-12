package dev.imran.user_authentication.Service;

import dev.imran.user_authentication.Models.Token;
import dev.imran.user_authentication.Models.User;
import dev.imran.user_authentication.Repository.TokenRepository;
import dev.imran.user_authentication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User signUp(String email, String password, String username) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {

        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setName(username);

        return userRepository.save(user);
    }

    public Token login(String email, String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // throw user is not valid
            return null;
        }
        User user = optionalUser.get();
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // throw password is wrong
            return null;

        }
        Token token = new Token();
        token.setUser(user);
        token.setExpiryDate(get30DaysLaterDate());
        token.setValue(UUID.randomUUID().toString());

        return tokenRepository.save(token);
    }
    private Date get30DaysLaterDate() {

        Date date = new Date();

        // Convert date to calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Add 30 days
        calendar.add(Calendar.DAY_OF_MONTH, 30);


        // extract date from calendar
        return calendar.getTime();
    }

    public void logout(String token) {

        Optional<Token> tokenOptional
                = tokenRepository.findByValueAndIsDeleted(token, false);

        if (tokenOptional.isEmpty()) {
            // throw an exception saying token is not present or already deleted.
            return ;
        }

        Token updatedToken = tokenOptional.get();
        updatedToken.setDeleted(true);
        tokenRepository.save(updatedToken);

    }
    public boolean validateToken(String token) {

        /*
        1. Check if the token is present in db
        2. Check if the token is not deleted
        3. Check if the token is not expired
         */

        Optional<Token> tokenOptional =
                tokenRepository.findByValueAndIsDeletedAndExpiryDateGreaterThan(
                        token, false, new Date());

        return tokenOptional.isPresent();
    }
    }
