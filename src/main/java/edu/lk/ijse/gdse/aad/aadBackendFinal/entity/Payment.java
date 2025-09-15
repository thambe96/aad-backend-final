package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String paymentIntentId; // Stripe PaymentIntent id
    private long amount; // cents
    private String currency; // e.g. "usd"
    private String status; // PENDING, SUCCEEDED, FAILED
    private String payerEmail; // optional
    private LocalDateTime createdAt;


    @OneToOne(mappedBy = "payment")
    private Donation donation;






}
