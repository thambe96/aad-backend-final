package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class PetDogDTO {

    private int dogId;
    private String dogName;
    private String dogBreed;
    private int dogAge;
    private User owner;

    private List<MultipartFile> images;

    private List<PetDogImage> imageListFromDb;



}
