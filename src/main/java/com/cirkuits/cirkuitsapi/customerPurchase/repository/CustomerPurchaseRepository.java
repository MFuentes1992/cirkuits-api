package com.cirkuits.cirkuitsapi.customerPurchase.repository;

import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CustomerPurchaseRepository extends JpaRepository<CustomerPurchase, UUID> {

    CustomerPurchase findByUserId(UUID userId);
    CustomerPurchase findByAddressId(UUID addressId);
    @Query(value = "SELECT * FROM tbl_customer_purchase WHERE stripe_customer_id = ?1", nativeQuery = true)
    CustomerPurchase findCustomerPurchaseByStripeCustomerId(String stripeCustomerId);

    @Query(value = "SELECT * FROM tbl_customer_purchase WHERE stripe_subscription_id = ?1", nativeQuery = true)
    CustomerPurchase findCustomerPurchaseBySubscriptionId(String id);
}
