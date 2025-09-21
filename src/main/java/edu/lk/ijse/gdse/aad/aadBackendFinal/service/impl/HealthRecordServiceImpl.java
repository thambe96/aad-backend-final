package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;


import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.HealthRecordDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.HealthRecord;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceNotFoundException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.HealthRecordRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.TreatmentRequestRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.CloudinaryService;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HealthRecordServiceImpl implements HealthRecordService {

    private final CloudinaryService cloudinaryService;
    private final TreatmentRequestRepo treatmentRequestRepo;
    private final HealthRecordRepo healthRecordRepo;




    @Override
    public String uploadHealthRecords(int treatmentReqId, List<MultipartFile> healthRecords) {


        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(treatmentReqId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "TreatmentRequest with requestId " + treatmentReqId + " not found")
        );



        List<HealthRecord> healthRecordList = new ArrayList<>();


        for (MultipartFile healthRecodeFile : healthRecords) {

            try {
                Map uploadResult = cloudinaryService.uploadFile(healthRecodeFile);
                String imageUrl = uploadResult.get("secure_url").toString();
                String publicId = uploadResult.get("public_id").toString();

                System.out.println(imageUrl);
                System.out.println(publicId);

                HealthRecord healthRecord = new HealthRecord();

                healthRecord.setHealthRecordImgUrl(imageUrl);
                healthRecord.setPublicId(publicId);


                healthRecord.setTreatmentRequest(treatmentRequest);

                healthRecordRepo.save(healthRecord);



                healthRecordList.add(healthRecord);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


//        treatmentRequest.setHealthRecords(healthRecordList);


//        treatmentRequestRepo.save(treatmentRequest);



        return "Successfully uploaded health records";
    }

    @Override
    public List<HealthRecordDTO> getAllHealthRecords(int treatmentReqId) {

        System.out.println("hists the service layer!!");

        List<HealthRecord> healthRecordImagesList = healthRecordRepo.getAllHealthRecordImagesFromTreatmentId(treatmentReqId);

        System.out.println("Health Record Image List :: " + healthRecordImagesList);




        List<HealthRecordDTO> healthRecordDTOList = new ArrayList<>();

        for (HealthRecord healthRecordImage : healthRecordImagesList) {

            HealthRecordDTO healthRecordDTO = new HealthRecordDTO();
            healthRecordDTO.setHealthRecordImgUrl(healthRecordImage.getHealthRecordImgUrl());
            healthRecordDTO.setPublicId(healthRecordImage.getPublicId());
            healthRecordDTO.setTreatmentRequest(healthRecordImage.getTreatmentRequest());
            healthRecordDTOList.add(healthRecordDTO);

        }



        return healthRecordDTOList;
    }




}
