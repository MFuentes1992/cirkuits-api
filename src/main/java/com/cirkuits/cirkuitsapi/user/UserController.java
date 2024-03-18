package com.cirkuits.cirkuitsapi.user;

import com.auth0.jwk.JwkException;
import com.cirkuits.cirkuitsapi.Auth.AuthService;
import com.cirkuits.cirkuitsapi.Verify.Verify;
import com.cirkuits.cirkuitsapi.Verify.VerifyResponseV1;
import com.cirkuits.cirkuitsapi.login.Login;
import com.cirkuits.cirkuitsapi.user.model.UserErrorResponseV1;
import com.cirkuits.cirkuitsapi.user.model.UserResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    private final UserService userService;
    @Value("${cirkuits.auth0.jwks.uri}")
    private String jwkUri;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "api/v1/users")
    public ResponseEntity<Object> getUser(@RequestParam("email") String email) {
        Users user = userService.getUserEmail(email);
        if(user == null) {
            UserErrorResponseV1 error = new UserErrorResponseV1(500, "User not found");
            return ResponseEntity.internalServerError().body(error);
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping(path = "api/v1/login")
    public ResponseEntity<Object> loginForm(@RequestHeader("Authorization") String bearerToken, @RequestBody Login login) throws JwkException {
        String token = bearerToken.substring(7);
        AuthService authService = new AuthService(token, jwkUri);
        if(!authService.isValidToken() || authService.isTokenExpired()) throw new JwkException("Token is invalid or expired");
        Users user = userService.getLoginEmail(login.getEmail(), login.getPassword());
        if(user != null) {
            UserResponseV1 userResponse = new UserResponseV1(user.getUserID().toString(),  user.getFullName(), user.getUserName(), user.getEmail(), user.getMobile(), user.isActive(), user.getDob(), user.getGender());
            return ResponseEntity.ok().body(userResponse);
        } else {
            UserErrorResponseV1 error = new UserErrorResponseV1(500, "User not found");
            return ResponseEntity.internalServerError().body(error);
        }
    }

    @PostMapping(path = "api/v1/register")
    public ResponseEntity<String> postUser(@RequestBody Users user) throws RuntimeException {
        if(user == null) {
            return ResponseEntity.internalServerError().body("User is empty");
        }
        Users addedUser = userService.addNewUser(user);
        if(addedUser == null) {
            return ResponseEntity.internalServerError().body("User already exists");
        }
        return ResponseEntity.ok().body("User created successfully");
    }

    @CrossOrigin(origins = "https://static-res-0.s3.us-east-2.amazonaws.com")
    @PutMapping(path = "api/v1/verify")
    public ResponseEntity<Object> putVerify(@RequestBody Verify payload) {
        VerifyResponseV1 res = userService.verifyUser(payload.getEmail(), payload.isActive());
        if(res.getCode() == 200) {
            return ResponseEntity.ok().body(res);
        }
        return ResponseEntity.internalServerError().body(res);
    }

    @PutMapping(path = "api/v1/edit")
    public ResponseEntity<Object> updateUserInfo(@RequestHeader("Authorization") String bearerToken, @RequestBody Users user) throws JwkException{
        String token = bearerToken.substring(7);
        AuthService authService = new AuthService(token, jwkUri);
        if(!authService.isValidToken() || authService.isTokenExpired()) throw new JwkException("Token is invalid or expired");
        UserResponseV1 userResponse = userService.updateUser(user);
        if(userResponse != null) {
            return ResponseEntity.ok().body(userResponse);
        } else {
            UserErrorResponseV1 error = new UserErrorResponseV1(500, "User not found");
            return ResponseEntity.internalServerError().body(error);
        }
    }
}
