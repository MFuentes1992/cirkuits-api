package com.cirkuits.cirkuitsapi.address;

import com.cirkuits.cirkuitsapi.Auth.AuthService;
import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.model.error.ErrorResponse;
import com.cirkuits.cirkuitsapi.address.service.CustomerAddressService;
import com.cirkuits.cirkuitsapi.stripe.model.error.StripeErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerAddressController {
   @Autowired
    private CustomerAddressService customerAddressService;
    @Value("${cirkuits.auth0.jwks.uri}")
    private String jwkUri;

   @PostMapping("api/v1/customer-address")
    public ResponseEntity<Object> saveCustomerAddress(@RequestHeader("Authorization") String bearerToken, @RequestBody CustomerAddress customerAddress) throws Exception {

       AuthService authService = new AuthService(bearerToken.substring(7), jwkUri);
       if(!authService.isValidToken() || authService.isTokenExpired()) {
           StripeErrorResponse errorResponse = new StripeErrorResponse("Token is invalid or expired.");
           return ResponseEntity.badRequest().body(errorResponse);
       };

       CustomerAddress savedAddress = customerAddressService.saveCustomerAddress(customerAddress);
        if(savedAddress == null){
            ErrorResponse errorResponse = new ErrorResponse("Server encounter an error and could not process the request.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error-message", errorResponse.toString()).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(savedAddress);
   }

   @PutMapping("api/v1/customer-address")
    public ResponseEntity<Object> updateCustomerAddress(@RequestHeader("Authorization") String bearerToken, @RequestBody CustomerAddress customerAddress) throws Exception {
       AuthService authService = new AuthService(bearerToken.substring(7), jwkUri);
       if(!authService.isValidToken() || authService.isTokenExpired()) {
           StripeErrorResponse errorResponse = new StripeErrorResponse("Token is invalid or expired.");
           return ResponseEntity.badRequest().body(errorResponse);
       };
       CustomerAddress updatedAddress = customerAddressService.updateCustomerAddress(customerAddress);
       if(updatedAddress == null){
           ErrorResponse errorResponse = new ErrorResponse("Server encounter an error and could not process the request.");
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("error-response", errorResponse.toString()).body(errorResponse);
       }
       return ResponseEntity.ok().body(updatedAddress);
   }
}
