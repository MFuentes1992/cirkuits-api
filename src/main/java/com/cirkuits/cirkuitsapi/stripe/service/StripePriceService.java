package com.cirkuits.cirkuitsapi.stripe.service;

import com.cirkuits.cirkuitsapi.stripe.model.StripeProductMapper;
import com.cirkuits.cirkuitsapi.stripe.model.StripeProductResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.Price;
import com.stripe.model.PriceCollection;
import com.stripe.model.Product;
import com.stripe.param.PriceListParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StripePriceService {

    public PriceCollection getAllPrices(int limit) throws StripeException {
        PriceListParams params = PriceListParams.builder().setLimit((long)limit).build();
        return Price.list(params);
    }

    public StripeProductResponse getProductMapper() throws StripeException  {
        ArrayList<StripeProductMapper> products = new ArrayList<>();
        PriceCollection collection = getAllPrices(10);
        collection.getData().forEach(price -> {
            try {
                Product product = Product.retrieve(price.getProduct());
                StripeProductMapper mapper = new StripeProductMapper();
                mapper.setId(price.getId());
                mapper.setPrice(price.getUnitAmount());
                mapper.setCurrency(price.getCurrency());
                mapper.setActive(price.getActive());
                mapper.setName(product.getName());
                mapper.setDescription(product.getDescription());
                mapper.setDuration(price.getRecurring().getInterval());
                products.add(mapper);
            } catch (StripeException e) {
                e.printStackTrace();
            }
        });
        StripeProductResponse response = new StripeProductResponse();
        response.setProducts(products);
        return response;
    }
}
