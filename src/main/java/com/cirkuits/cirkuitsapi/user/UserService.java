package com.cirkuits.cirkuitsapi.user;

import com.cirkuits.cirkuitsapi.EmailService.EmailService;
import com.cirkuits.cirkuitsapi.Verify.VerifyResponseV1;
import com.cirkuits.cirkuitsapi.user.model.UserResponseV1;
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
        try {
            mailService.sendEmail("services@mail.prod.cirkuits.com", user.getEmail(), "Verify Email", "verifyEmail");
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return userRepo.save(user);
    }

    public VerifyResponseV1 verifyUser(String email, boolean active) {
        VerifyResponseV1 response = new VerifyResponseV1(0, "");
        Users user = userRepo.findByEmail(email);
        if(user == null) {
            response.setCode(500);
            response.setMessage("Something went wrong");
            return response;
        }
        if(user.isActive()) {
            response.setCode(500);
            response.setMessage("User is already activated.");
            return response;
        };
        user.setActive(active);
        userRepo.save(user);
        response.setCode(200);
        response.setMessage("User has been activated successfully");
        return response;
    }

    public UserResponseV1 updateUser(Users user) {
        Users existingUser = userRepo.findById(user.getUserID()).orElse(null);
        if(existingUser == null) {
            return null;
        }
        Users saved = userRepo.save(user);
        return new UserResponseV1(saved.getFullName(), saved.getUserName(), saved.getEmail(), saved.getMobile(), saved.isActive());
    }

}
