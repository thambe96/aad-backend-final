package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import java.util.Map;

public interface PaymentService {

    Map<String, Object> createPaymentIntent(Long amountCents, String currency, int requestId, int donorUserId);


}
