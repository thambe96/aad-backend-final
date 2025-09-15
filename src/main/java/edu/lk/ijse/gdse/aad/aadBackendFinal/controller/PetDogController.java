package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.PetDogDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.TestDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.PetDogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/petDog")
@RequiredArgsConstructor
@CrossOrigin
public class PetDogController {

    private final PetDogService petDogService;

    @PostMapping("/createPetDog")
    public ResponseEntity<ApiResponse> createPetDog(
            @RequestPart("images") List<MultipartFile> images,
            @RequestPart("details") PetDogDTO petDogDTO) {

        petDogDTO.setImages(images);

        petDogService.savePetDog(petDogDTO);


        return null;
    }


    @DeleteMapping("/deletePetDog/{petDogId}")
    public ResponseEntity<ApiResponse> deletePetDog(@PathVariable Integer petDogId) {
        petDogService.deletePetDog(petDogId);
        return null;
    }

    @GetMapping("/getPetDog/{petDogId}")
    public ResponseEntity<ApiResponse> getPetDog(@PathVariable Integer petDogId) {

        PetDogDTO petDogDTO = petDogService.getPetDog(petDogId);

        // remove this
        System.out.println(petDogDTO);

        String status = "ok";

        if (petDogDTO == null) {
            // throw an exception
            status = "not found";
        }

        return new ResponseEntity<>(
                new ApiResponse(200, status, petDogDTO),
                HttpStatus.FOUND);
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getAllPetDogsBelongToUser(@PathVariable Integer userId) {

        List<PetDogDTO> petDogDTOS = petDogService.getAllPetDogsUserBelong(userId);

        System.out.println(petDogDTOS);

        // Handle exceptions -->

        return new ResponseEntity<>(new ApiResponse(200, "ok", petDogDTOS), HttpStatus.OK);
    }

    @PutMapping("/updatePetDog")
    public ResponseEntity<ApiResponse> updatePetDog(
            @RequestBody PetDogDTO petDogDTO) {
        // Service method call
        petDogService.updatePetDog(petDogDTO);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Pet dog Updated", null));
    }



}
