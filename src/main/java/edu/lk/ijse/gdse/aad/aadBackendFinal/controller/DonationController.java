package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;


import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.DonationDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Donation;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.DonationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @PostMapping("/crateDonation")
    public ResponseEntity<ApiResponse> createDonation(@RequestBody DonationDTO donationDTO) {

        String result = donationService.createDonation(donationDTO);

        return new ResponseEntity<>(new ApiResponse(201, "ok", result), HttpStatus.CREATED);
    }



    @GetMapping("/donorsDonationHistory/{userId}")
    public ResponseEntity<ApiResponse> getDonationById(@PathVariable("userId") int donationId) {

        //Code goes here
        List<DonationDTO> donationDTOS = donationService.getAllDonationsByUserId(donationId);
        return new ResponseEntity<>(new ApiResponse(200, "ok", donationDTOS), HttpStatus.OK);
    }



}
