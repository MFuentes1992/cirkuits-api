package com.cirkuits.cirkuitsapi.address.service;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.repository.CustomerAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerAddressService {
    private CustomerAddressRepository customerAddressRepository;
@Autowired
    public CustomerAddressService(CustomerAddressRepository customerAddressRepository) {
        this.customerAddressRepository = customerAddressRepository;
    }

    public CustomerAddress saveCustomerAddress(CustomerAddress customerAddress) {
        return customerAddressRepository.save(customerAddress);
    }

    public CustomerAddress getCustomerAddress(UUID userId) {
        return customerAddressRepository.findByUserId(userId);
    }

}
