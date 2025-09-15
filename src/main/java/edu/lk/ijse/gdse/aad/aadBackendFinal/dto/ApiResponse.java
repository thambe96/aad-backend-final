package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data

public class ApiResponse {

    private int status;
    private String message;
    private Object data;

}
