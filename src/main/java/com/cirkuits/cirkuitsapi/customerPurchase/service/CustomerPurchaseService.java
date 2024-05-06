package com.cirkuits.cirkuitsapi.customerPurchase.service;

import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import com.cirkuits.cirkuitsapi.customerPurchase.repository.CustomerPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerPurchaseService {
    @Autowired
    private final CustomerPurchaseRepository customerPurchaseRepository;

    public CustomerPurchaseService(CustomerPurchaseRepository customerPurchaseRepository) {
        this.customerPurchaseRepository = customerPurchaseRepository;
    }

    public void saveCustomerPurchase(CustomerPurchase customerPurchase) {
        customerPurchaseRepository.save(customerPurchase);
    }

    public CustomerPurchase getCustomerPurchaseByAddressId(UUID addressId) {
        return customerPurchaseRepository.findByAddressId(addressId);
    }

    public CustomerPurchase getCustomerPurchase(UUID id) {
        return customerPurchaseRepository.findByUserId(id);
    }

    public void updateCustomerPurchase(CustomerPurchase customerPurchase) {
        customerPurchaseRepository.save(customerPurchase);
    }
}
