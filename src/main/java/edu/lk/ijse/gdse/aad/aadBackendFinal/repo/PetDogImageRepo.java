package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//@ -> annotation was added later

@Repository
public interface PetDogImageRepo extends JpaRepository<PetDogImage, Integer> {

/*
    @Query(value = "SELECT * FROM pet_dog_image where pet_dog_image_Id= :petDogId" , nativeQuery = true)
    PetDogImage findByPetDogImage(@Param("petDogId") int petDogId);

    */


    @Query(value = "SELECT * FROM pet_dog_image where dog_id= :petDogId" , nativeQuery = true)
    List<PetDogImage> findByPetDogIdDogImages(@Param("petDogId") int petDogId);


}
