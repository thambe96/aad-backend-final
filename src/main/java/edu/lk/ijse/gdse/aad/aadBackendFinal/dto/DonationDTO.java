package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Payment;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data

public class DonationDTO {

    private int donationId;

    private long amount;

    private LocalDate date;

    private User donator;

    private TreatmentRequest treatmentRequest;

    private Payment payment;


    public DonationDTO(long amount, User donator, TreatmentRequest treatmentRequest) {

        this.amount = amount;
        this.donator = donator;
        this.treatmentRequest = treatmentRequest;

    }



}
