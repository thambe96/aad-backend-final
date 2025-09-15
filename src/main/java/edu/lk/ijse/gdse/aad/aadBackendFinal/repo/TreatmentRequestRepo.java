package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.RequestPetCardDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.TreatmentRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface TreatmentRequestRepo extends JpaRepository<TreatmentRequest, Integer> {

    @Query(value = "select treatment_request.* " +
            "from user " +
            "join pet_dog " +
            "on pet_dog.owner_id = user.id " +
            "join treatment_request " +
            "on pet_dog.dog_id = treatment_request.dog_id " +
            "where id= :userId",
            nativeQuery = true)
    List<TreatmentRequest> getAllRequestByUserId(@Param("userId") int userId);




    @Query(value = "select " +
            "treatment_request.*," +
            "pet_dog_image.pet_dog_image_url," +
            "pet_dog.dog_name " +
            "from treatment_request " +
            "join pet_dog " +
            "on pet_dog.dog_id = treatment_request.dog_id " +
            "join pet_dog_image " +
            "on pet_dog.dog_id = pet_dog_image.dog_id ",
            nativeQuery = true)
    List<Object[]> getAllTreatmentRequests();






}
