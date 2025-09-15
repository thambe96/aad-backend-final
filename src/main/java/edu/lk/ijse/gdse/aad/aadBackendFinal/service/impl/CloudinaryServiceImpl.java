package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;


    //Handle in GlobalExceptionHandler
    @Override
    public Map uploadFile(MultipartFile file) throws IOException {

        // Handle image size exceeding issue

        return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
    }

    @Override
    public void deleteFile(String publicId) throws IOException {
        cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }


}
