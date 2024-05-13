package com.cirkuits.cirkuitsapi.address.service;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.repository.CustomerAddressRepository;
import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import com.cirkuits.cirkuitsapi.customerPurchase.service.CustomerPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;
    private CustomerPurchaseService customerPurchaseService;
@Autowired
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository, CustomerPurchaseService customerPurchaseService) {
        this.customerAddressRepository = customerAddressRepository;
        this.customerPurchaseService = customerPurchaseService;
    }

    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) {
        CustomerPurchase customerPurchase = customerPurchaseService.getCustomerPurchase(customerAddress.getUserId());
        CustomerAddress savedAddress = customerAddressRepository.save(customerAddress);
        if(customerPurchase != null){
            customerPurchase.setAddressId(savedAddress.getAddressId());
            customerPurchaseService.updateCustomerPurchase(customerPurchase);
        }
        return savedAddress;
    }

    public CustomerAddress getCustomerAddress(UUID userId) {
        return customerAddressRepository.findByUserId(userId);
    }

}
