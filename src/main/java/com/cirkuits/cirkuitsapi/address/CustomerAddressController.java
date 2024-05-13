package com.cirkuits.cirkuitsapi.address;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerAddressController {
   @Autowired
    private CustomerAddressService customerAddressService;

   @PostMapping("api/v1/customerAddress")
    public ResponseEntity<Object> saveCustomerAddress(@RequestBody CustomerAddress customerAddress) throws Exception {
        CustomerAddress savedAddress = customerAddressService.saveCustomerAddress(customerAddress);
        if(savedAddress == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Address not saved");
        }
        return ResponseEntity.status(HttpStatus.OK).body(savedAddress);
   }
}
