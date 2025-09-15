package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class RequestPetCardDTO {

    private int requestId;
    private String requestStatus;
    private double collectedAmount;
    private String treatmentDescription;
    private double treatmentPrice;
    private int dogId;
    private String requestName;
    private String petImageUrl;
    private String petName;





}
