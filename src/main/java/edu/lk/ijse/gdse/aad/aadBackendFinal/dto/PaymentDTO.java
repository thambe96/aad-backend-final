package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class PaymentDTO {


    private Long amountCents;
    private String currency;
    private Integer requestId;
    private Integer donorUserId;


}
