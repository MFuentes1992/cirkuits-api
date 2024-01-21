package com.cirkuits.cirkuitsapi.user;

import com.cirkuits.cirkuitsapi.EmailService.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final EmailService mailService;
    @Autowired
    public UserService(UsersRepository _userRepo, EmailService mailService) {
        this.userRepo = _userRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.mailService = mailService;
    }
    public Users getUserEmail(String email) {
        return userRepo.findByEmail(email);
    }
    public Users getLoginEmail(String email, String password) {
        Users userEmail = userRepo.findByEmail(email);
        if(userEmail != null && passwordEncoder.matches(password, userEmail.getPassword())) {
            return userEmail;
        }
        return null;
    }
    public Users addNewUser(Users user) {
        Users existingEmail = userRepo.findByEmail(user.getEmail());
        Users existingUser = userRepo.findByUserName(user.getUserName());
        if (existingUser != null || existingEmail != null) {
            return null;
        }
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        Users addedUser = userRepo.save(user);
        try {
            mailService.sendEmail("services@mail.prod.cirkuits.com", user.getEmail(), "Verify Email", "verifyEmail.com");
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return addedUser;
    }
}
