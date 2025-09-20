package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.TreatmentRequestDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentReqStatus;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.TreatmentRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/treatmentReqController")
@RequiredArgsConstructor
@CrossOrigin()
public class TreatmentRequestController {

    private final TreatmentRequestService treatmentRequestService;


    @PostMapping("/createTreatmentRequest")
    public ResponseEntity<ApiResponse> createTreatmentRequest(



            @RequestParam String treatmentName,
            @RequestParam String treatmentDescription,
            @RequestParam double treatmentPrice,
            @RequestParam String requestStatus,
            @RequestParam int dogId,
            @RequestParam String dogName,
            @RequestParam String dogBreed,
            @RequestParam int dogAge




    ) {



        // Create DTO manually


        TreatmentRequestDTO treatmentRequestDTO = new TreatmentRequestDTO();
        treatmentRequestDTO.setTreatmentName(treatmentName);
        treatmentRequestDTO.setTreatmentDescription(treatmentDescription);
        treatmentRequestDTO.setTreatmentPrice(treatmentPrice);
        treatmentRequestDTO.setRequestStatus(TreatmentReqStatus.valueOf(requestStatus));

        PetDog petDog = new PetDog();
        petDog.setDogId(dogId);
        petDog.setDogName(dogName);
        petDog.setDogBreed(dogBreed);
        petDog.setDogAge(dogAge);

        treatmentRequestDTO.setPetDog(petDog);
        treatmentRequestDTO.setCollectedAmount(0.0);








        String result =  treatmentRequestService.createTreatmentRequest(treatmentRequestDTO);

//        String result = "Success!!";

//        System.out.println("Hi HI this works fine");

        return ResponseEntity.ok(new ApiResponse(HttpStatus.CREATED.value(), "ok", result));
    }








    @GetMapping("/getRequestbyId/{requestId}")
    public ResponseEntity<ApiResponse> getRequestById(@PathVariable("requestId") int requestId) {

        TreatmentRequestDTO treatmentRequestDTO = treatmentRequestService.getTreatmentRequestbyId(requestId);

        return new ResponseEntity<>(new ApiResponse(200, "ok", treatmentRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/requestsByUserId/{userId}")
    public ResponseEntity<ApiResponse> getAllRequestsBelongToUser(@PathVariable int userId) {

        //Code goes here

        List<TreatmentRequestDTO> treatmentRequestDTOList = treatmentRequestService.getAllTreatmentRequestsByUserId(userId);

        return new ResponseEntity<>(new ApiResponse(200, "ok", treatmentRequestDTOList), HttpStatus.OK);
    }

    @PutMapping("/UpdateRequest")
    public ResponseEntity<ApiResponse> updateRequest(@RequestBody TreatmentRequestDTO treatmentRequestDTO) {

        //call the service method
        treatmentRequestService.updateTreatmentRequest(treatmentRequestDTO);

        return ResponseEntity.ok(
                new ApiResponse( HttpStatus.OK.value(), "Request Updated!", null));
    }

    @DeleteMapping("/deleteRequest/{requestId}")
    public ResponseEntity<ApiResponse> deleteRequest(@PathVariable int requestId) {
        //call service method
        treatmentRequestService.deleteTreatmentRequest(requestId);
        return ResponseEntity.ok(
                new ApiResponse( HttpStatus.OK.value(), "Request Updated!", null));
    }


    @GetMapping("/getAllRequests")
    public ResponseEntity<ApiResponse> getAllRequests() {

        return  ResponseEntity.ok(
                new ApiResponse( HttpStatus.OK.value(), "ok", treatmentRequestService.getAllTreatmentRequests())
        );
    }





}
