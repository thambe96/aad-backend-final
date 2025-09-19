package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;


import edu.lk.ijse.gdse.aad.aadBackendFinal.service.HealthRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/healthRecord")
@RequiredArgsConstructor
@CrossOrigin

public class HealthRecordController {


    private final HealthRecordService healthRecordService;


    @GetMapping("/saveHealthRecords")
    public String saveHealthRecords(@PathVariable Integer treatmentReqId, @RequestParam("healthRecords") List<MultipartFile> file) {

        String result = healthRecordService.uploadHealthRecords(treatmentReqId, file);

        System.out.println(result);

        return null;
    }



}
