package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.PetDogDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;

import java.util.List;

public interface PetDogService {

    void savePetDog(PetDogDTO petDogDTO);
    void deletePetDog(int petDogId);
    PetDogDTO getPetDog(int petDogId);
    List<PetDogDTO> getAllPetDogsUserBelong(int userId);
    String updatePetDog(PetDogDTO petDogDTO);

}
