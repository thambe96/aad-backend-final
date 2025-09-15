package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data

public class PetDogImageDTO {

    private int petDogImageId;
    private String petDogImageUrl;
    private String publicId;
    private PetDog petDog;


}
