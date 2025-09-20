package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentReqStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor

public class TreatmentRequestDTO {

    private int requestId;
    private String treatmentName;
    private String treatmentDescription;
    private double treatmentPrice;
    private double collectedAmount;
    private TreatmentReqStatus requestStatus;
    private PetDog petDog;
}
