package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

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

}
