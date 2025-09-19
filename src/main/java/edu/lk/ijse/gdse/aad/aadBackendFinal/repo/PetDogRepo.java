package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDogImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
// @ -> annotation was added later
@Repository
public interface PetDogRepo extends JpaRepository<PetDog, Integer> {


/*

    @Query(value = "SELECT * FROM pet_dog_image where dog_id= :petDogId" , nativeQuery = true)
    List<PetDogImage> findByPetDogIdDogImages(@Param("petDogId") int petDogId);

*/

    @Query(value = "SELECT * FROM pet_dog WHERE owner_id= :userId", nativeQuery = true)
    List<PetDog> findPetDogByOwnerId(@Param("userId") Integer userId);



}
