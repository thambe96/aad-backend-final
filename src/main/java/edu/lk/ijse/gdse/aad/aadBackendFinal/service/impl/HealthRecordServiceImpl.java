package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;


import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.HealthRecord;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceNotFoundException;
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




    @Override
    public String uploadHealthRecords(int treatmentReqId, List<MultipartFile> healthRecords) {


        TreatmentRequest treatmentRequest = treatmentRequestRepo.findById(treatmentReqId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "TreatmentRequest with requestId " + treatmentReqId + " not found")
        );


        // A list from HealthRecord entity
        List<HealthRecord> healthRecordList = new ArrayList<>();


        for (MultipartFile healthRecodeFile : healthRecords) {

            try {
                Map uploadResult = cloudinaryService.uploadFile(healthRecodeFile);
                String imageUrl = uploadResult.get("secure_url").toString();
                String publicId = uploadResult.get("public_id").toString();

                HealthRecord healthRecord = new HealthRecord();

                healthRecord.setHealthRecordImgUrl(imageUrl);
                healthRecord.setPublicId(publicId);



//
//                petDogImage.setPetDogImageUrl(imageUrl);
//                petDogImage.setPublicId(publicId);
//




                //set the PetDog to the PetDogImage
//                petDogImage.setPetDog(petDog);

                healthRecord.setTreatmentRequest(treatmentRequest);




//                petDogImages.add(petDogImage);

                healthRecordList.add(healthRecord);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

//        petDog.setImages(petDogImages);
//        petDogRepo.save(petDog);

//

        treatmentRequest.setHealthRecords(healthRecordList);
        treatmentRequestRepo.save(treatmentRequest);



        return "Successfully uploaded health records";
    }
}
