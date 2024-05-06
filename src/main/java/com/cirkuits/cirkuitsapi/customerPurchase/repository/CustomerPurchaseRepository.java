package com.cirkuits.cirkuitsapi.customerPurchase.repository;

import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, UUID> {

    CustomerPurchase findByUserId(UUID userId);

}
