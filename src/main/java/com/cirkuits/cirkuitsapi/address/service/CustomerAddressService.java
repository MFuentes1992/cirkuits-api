package com.cirkuits.cirkuitsapi.address.service;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.repository.CustomerAddressRepository;
import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import com.cirkuits.cirkuitsapi.customerPurchase.service.CustomerPurchaseService;
import com.cirkuits.cirkuitsapi.user.UserService;
import com.cirkuits.cirkuitsapi.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;
    private CustomerPurchaseService customerPurchaseService;
    private UserService userService;
@Autowired
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository, CustomerPurchaseService customerPurchaseService, UserService userService) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerPurchaseService = customerPurchaseService;
        this.userService = userService;
    }

    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) {
        Users existingUser = userService.getUserById(customerAddress.getUserId());
        if(existingUser == null) {
            return null;
        }

        CustomerAddress exisitingCustomerAddress = customerAddressRepository.findByUserId(existingUser.getUserID());
        if(exisitingCustomerAddress != null) {
            return exisitingCustomerAddress;
        }

        CustomerPurchase customerPurchase = customerPurchaseService.getCustomerPurchase(customerAddress.getUserId());
        CustomerAddress savedAddress = customerAddressRepository.save(customerAddress);
        if(customerPurchase != null){
            customerPurchase.setAddressId(savedAddress.getAddressId());
            customerPurchaseService.updateCustomerPurchase(customerPurchase);
        }
        return savedAddress;
    }

    public CustomerAddress updateCustomerAddress(CustomerAddress customerAddress) {
        Users existingUser = userService.getUserById(customerAddress.getUserId());
        if (existingUser == null) {
            return null;
        }
        CustomerAddress exisitingCustomerAddress = customerAddressRepository.findByUserId(existingUser.getUserID());
        if (exisitingCustomerAddress == null) {
            return null;
        }
        customerAddress.setAddressId(exisitingCustomerAddress.getAddressId());
        CustomerAddress savedAddress = customerAddressRepository.save(customerAddress);
        return savedAddress;
    }

    public CustomerAddress getCustomerAddress(UUID userId) {
        return customerAddressRepository.findByUserId(userId);
    }

}
