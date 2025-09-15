package edu.lk.ijse.gdse.aad.aadBackendFinal.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {

    @Value("${stripe.secret}")
    private String stripeSecret;


    @PostConstruct
    public void initStripe() {
        com.stripe.Stripe.apiKey = stripeSecret;
    }

}
