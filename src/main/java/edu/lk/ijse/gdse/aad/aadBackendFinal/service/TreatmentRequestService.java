package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.RequestPetCardDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.TreatmentRequestDTO;

import java.util.List;


public interface TreatmentRequestService {

    String createTreatmentRequest(TreatmentRequestDTO treatmentRequestDTO);

    TreatmentRequestDTO getTreatmentRequestbyId(int requestId);

    List<RequestPetCardDTO> getAllTreatmentRequestsByUserId(int userId);

    String updateTreatmentRequest(TreatmentRequestDTO treatmentRequestDTO);
    String deleteTreatmentRequest(int requestId);

    List<RequestPetCardDTO> getAllTreatmentRequests();


}
