package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    Map uploadFile(MultipartFile file) throws IOException;
    void deleteFile(String publicId) throws IOException;


}
