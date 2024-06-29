package com.cirkuits.cirkuitsapi.stripe.service;

import com.cirkuits.cirkuitsapi.address.model.CustomerAddress;
import com.cirkuits.cirkuitsapi.address.service.CustomerAddressService;
import com.cirkuits.cirkuitsapi.customerPurchase.model.CustomerPurchase;
import com.cirkuits.cirkuitsapi.customerPurchase.service.CustomerPurchaseService;
import com.cirkuits.cirkuitsapi.stripe.model.StripeBillingResponse;
import com.cirkuits.cirkuitsapi.stripe.model.StripeCustomerResponse;
import com.cirkuits.cirkuitsapi.stripe.model.StripeResponse;
import com.cirkuits.cirkuitsapi.user.UserService;
import com.cirkuits.cirkuitsapi.user.Users;
import com.stripe.model.Customer;
import com.stripe.model.EphemeralKey;
import com.stripe.model.PaymentIntent;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.EphemeralKeyCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
public class StripeService {
    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomerPurchaseService customerPurchaseService;
    @Autowired
    private final CustomerAddressService customerAddressService;
    private Users user;
    private String currency;
    private Long amount;

    private String locale;

    public StripeService(UserService userService, CustomerPurchaseService customerPurchaseService, CustomerAddressService customerAddressService) {
        this.userService = userService;
        this.customerPurchaseService = customerPurchaseService;
        this.customerAddressService = customerAddressService;
    }

    public void Initialize(Users user, String currency, Long amount, String locale) {
        this.user = user;
        this.currency = currency;
        this.amount = amount;
        this.locale = locale;
    }

    public Customer createCustomer(Users _user) throws Exception {
        CustomerPurchase customerPurchase = customerPurchaseService.getCustomerPurchase(_user.getUserID());
        CustomerAddress customerAddress = customerAddressService.getCustomerAddress(_user.getUserID());
        if(customerPurchase != null && customerPurchase.getStripeCustomerId() != null) {
            return Customer.retrieve(customerPurchase.getStripeCustomerId());
        }
        CustomerCreateParams.Builder builder = new CustomerCreateParams.Builder()
                .setEmail(_user.getEmail())
                .setName(_user.getFullName())
                .setPhone(_user.getMobile())
                .addPreferredLocale(locale);
        if(customerAddress != null) {
            builder.setAddress(CustomerCreateParams.Address.builder()
                    .setCity(customerAddress.getCity())
                    .setCountry(customerAddress.getCountry())
                    .setLine1(customerAddress.getLine1())
                    .setLine2(customerAddress.getLine2())
                    .setPostalCode(customerAddress.getPostalCode())
                    .setState(customerAddress.getState())
                    .build());
        }
        CustomerCreateParams customerCreateParams = builder.build();
        Customer customer = Customer.create(customerCreateParams);
        customerPurchaseService.saveCustomerPurchase(new CustomerPurchase(_user.getUserID(),
                currency, locale, customerAddress!= null ? customerAddress.getAddressId() : null, customer.getId()));
        return customer;
    }

    public EphemeralKey createEphemeralKey(Customer customer) throws Exception {
        EphemeralKeyCreateParams keyCreateParams = new EphemeralKeyCreateParams.Builder()
                .setCustomer(customer.getId())
                .setStripeVersion("2020-08-27")
                .build();
        return EphemeralKey.create(keyCreateParams);
    }

    public StripeResponse createPaymentIntent() throws Exception {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency(currency)
                .setAmount(amount)
                .setAutomaticPaymentMethods(PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build())
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        EphemeralKey ephemeralKey = createEphemeralKey(createCustomer(user));
        Customer customer = createCustomer(user);
        return new StripeResponse(intent.getClientSecret(), ephemeralKey.getSecret(), customer.getId());
    }

    public StripeBillingResponse createSubscriptionIntent(String priceId, String customerId) throws Exception {
        SubscriptionCreateParams.PaymentSettings paymentSettings = SubscriptionCreateParams.PaymentSettings.builder().setSaveDefaultPaymentMethod(SubscriptionCreateParams.PaymentSettings.SaveDefaultPaymentMethod.ON_SUBSCRIPTION).build();
        SubscriptionCreateParams createParams = SubscriptionCreateParams.builder().setCustomer(customerId).addItem(
                SubscriptionCreateParams.Item.builder().setPrice(priceId).build()
        ).setPaymentSettings(paymentSettings).setPaymentBehavior(SubscriptionCreateParams.PaymentBehavior.DEFAULT_INCOMPLETE)
                .addAllExpand(Arrays.asList("latest_invoice.payment_intent"))
                .build();
        Subscription subscription = Subscription.create(createParams);
        StripeBillingResponse response = new StripeBillingResponse(subscription.getId(), subscription.getLatestInvoiceObject().getPaymentIntentObject().getClientSecret());
        return response;
    }

    public StripeCustomerResponse createStripeCustomer(String email) throws Exception {
        StripeCustomerResponse response = new StripeCustomerResponse();
        Users existingUser = userService.getUserEmail(email);

        if(existingUser == null) {
            return null;
        }

        CustomerPurchase customerPurchase = customerPurchaseService.getCustomerPurchase(existingUser.getUserID());
        if(customerPurchase != null) {
            response.setCustomerId(customerPurchase.getStripeCustomerId());
            response.setCustomerName(existingUser.getFullName());
            return response;
        }
        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(existingUser.getFullName())
                .setEmail(email)
                .build();
        Customer customer = Customer.create(params);
        customerPurchaseService.saveCustomerPurchase(new CustomerPurchase(existingUser.getUserID(), "", "", null, customer.getId()));
        response.setCustomerId(customer.getId());
        response.setCustomerName(customer.getName());
        return response;
    }

}
