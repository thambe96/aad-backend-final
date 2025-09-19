package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.HealthRecord;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HealthRecordService {


    String uploadHealthRecords(int treatmentReqId, List<MultipartFile> healthRecords);



}
