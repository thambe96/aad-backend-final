package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.RequestPetCardDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.TreatmentRequestDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceNotFoundException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.TreatmentRequestRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.TreatmentRequestService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TreatmentRequestServiceImpl implements TreatmentRequestService {

    private final TreatmentRequestRepo treatmentRequestRepo;
    private final ModelMapper modelMapper;

    @Override
    public String createTreatmentRequest(TreatmentRequestDTO treatmentRequestDTO) {

        //Handle Exceptions
        treatmentRequestRepo.save(modelMapper.map(treatmentRequestDTO, TreatmentRequest.class));

        return "Treatment Request Created Successfully";
    }



    @Override
    public TreatmentRequestDTO getTreatmentRequestbyId(int requestId) {

        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(requestId).orElse(null);

        if (treatmentRequest == null) {
            // throw an exception
        }

        TreatmentRequestDTO treatmentRequestDTO = new TreatmentRequestDTO(
                treatmentRequest.getRequestId(),
                treatmentRequest.getTreatmentName(),
                treatmentRequest.getTreatmentDescription(),
                treatmentRequest.getTreatmentPrice(),
                treatmentRequest.getCollectedAmount(),
                treatmentRequest.getRequestStatus(),
                treatmentRequest.getPetDog()
        );

        return treatmentRequestDTO;
    }







    @Override
    public List<RequestPetCardDTO> getAllTreatmentRequestsByUserId(int userId) {


        /*
        List<TreatmentRequest> treatmentRequests = treatmentRequestRepo.getAllRequestByUserId(userId);

        List<TreatmentRequestDTO> treatmentRequestDTOS = new ArrayList<>();
        for (TreatmentRequest treatmentRequest : treatmentRequests) {
            TreatmentRequestDTO treatmentRequestDTO = new TreatmentRequestDTO(
                    treatmentRequest.getRequestId(),
                    treatmentRequest.getTreatmentName(),
                    treatmentRequest.getTreatmentDescription(),
                    treatmentRequest.getTreatmentPrice(),
                    treatmentRequest.getCollectedAmount(),
                    treatmentRequest.getRequestStatus(),
                    treatmentRequest.getPetDog()
            );
            treatmentRequestDTOS.add(treatmentRequestDTO);
        }

         */



        List<Object[]> results = treatmentRequestRepo.getAllRequestByUserId(userId);


        List<RequestPetCardDTO> allRequestDtos = results.stream().map(r ->
                new RequestPetCardDTO(
                        (Integer) r[0],
                        (String) r[1],
                        (Double) r[2],
                        (String) r[3],
                        (Double) r[4],
                        (Integer) r[5],
                        (String) r[6],
                        (String) r[7],
                        (String) r[8])).toList();



/*
        for (RequestPetCardDTO dto : dtos) {
            System.out.println(dto.getRequestName());
            System.out.println(dto.getRequestStatus());
            System.out.println(dto.getRequestId());
            System.out.println(dto.getPetImageUrl());
        }
        */


        return allRequestDtos;



//        return treatmentRequestDTOS;
    }















    @Override
    public String updateTreatmentRequest(TreatmentRequestDTO treatmentRequestDTO) {

        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(treatmentRequestDTO.getRequestId()).
                orElseThrow(()-> new ResourceNotFoundException("Request not found"));

        treatmentRequest.setRequestStatus(treatmentRequestDTO.getRequestStatus());
        treatmentRequest.setTreatmentDescription(treatmentRequestDTO.getTreatmentDescription());
        treatmentRequest.setCollectedAmount(treatmentRequestDTO.getCollectedAmount());
        treatmentRequest.setTreatmentPrice(treatmentRequestDTO.getTreatmentPrice());

        treatmentRequestRepo.save(treatmentRequest);


        return "Treatment Request Updated Successfully";
    }

    @Override
    public String deleteTreatmentRequest(int requestId) {
        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(requestId).orElseThrow(
                ()-> new ResourceNotFoundException("Request not found"));
        treatmentRequestRepo.delete(treatmentRequest);
        return "Treatment Request Deleted Successfully";
    }

    @Override
    public List<RequestPetCardDTO> getAllTreatmentRequests() {

//        List<TreatmentRequest> treatmentRequests = treatmentRequestRepo.findAll();

        List<Object[]> results = treatmentRequestRepo.getAllTreatmentRequests();


        List<RequestPetCardDTO> allRequestDtos = results.stream().map(r ->
                new RequestPetCardDTO(
                                        (Integer) r[0],
                                        (String) r[1],
                                        (Double) r[2],
                                        (String) r[3],
                                        (Double) r[4],
                                        (Integer) r[5],
                                        (String) r[6],
                                                (String) r[7],
                                        (String) r[8])).toList();



/*
        for (RequestPetCardDTO dto : dtos) {
            System.out.println(dto.getRequestName());
            System.out.println(dto.getRequestStatus());
            System.out.println(dto.getRequestId());
            System.out.println(dto.getPetImageUrl());
        }
        */


        return allRequestDtos;
    }












}
