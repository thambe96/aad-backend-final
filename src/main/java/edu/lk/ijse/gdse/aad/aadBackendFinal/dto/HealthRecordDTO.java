package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor


public class HealthRecordDTO {

    private int healthRecordId;
    private String healthRecordImgUrl;
    private String publicId;
    private TreatmentRequest treatmentRequest;


}
