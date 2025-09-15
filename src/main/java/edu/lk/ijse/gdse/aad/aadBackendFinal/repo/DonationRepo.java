package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<Donation, Integer> {


    @Query(value = "SELECT * FROM donation WHERE donator_id= :userId", nativeQuery = true)
    List<Donation> findByDonorId(@Param("userId") int userId);


}
