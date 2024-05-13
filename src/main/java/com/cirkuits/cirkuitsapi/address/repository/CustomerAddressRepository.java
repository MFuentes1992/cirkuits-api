package com.cirkuits.cirkuitsapi.address.repository;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID>{
    CustomerAddress findByUserId(UUID userId);
}
