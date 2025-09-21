package edu.lk.ijse.gdse.aad.aadBackendFinal.repo;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HealthRecordRepo extends JpaRepository<HealthRecord, Integer> {


    @Query(value = "select * from health_record_image where treatment_req_id = :treatmentId", nativeQuery = true)
    List<HealthRecord> getAllHealthRecordImagesFromTreatmentId(@Param("treatmentId") int treatmentId);




}
