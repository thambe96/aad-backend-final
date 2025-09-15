package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;


import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.PaymentDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create-intent")
    public ResponseEntity<Map<String, Object>> createPaymentIntent(@RequestBody PaymentDTO paymentDTOReq) {
        try {
            Map<String, Object> data = paymentService.createPaymentIntent(
                    paymentDTOReq.getAmountCents(),
                    paymentDTOReq.getCurrency(),
                    paymentDTOReq.getRequestId(),
                    paymentDTOReq.getDonorUserId()
            );
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }




}
