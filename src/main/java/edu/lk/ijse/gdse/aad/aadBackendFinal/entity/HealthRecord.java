package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "health_record_image")



public class HealthRecord {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_record_img_id")
    private int healthRecordId;


    @Column(name = "health_record_img_url")
    private String healthRecordImgUrl;


    @Column(name = "public_id")
    private String publicId;



    @ManyToOne
    @JoinColumn(name = "treatment_req_id")
    @JsonBackReference("treatment-req-health-record")
    private TreatmentRequest treatmentRequest;




/*
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private PetDog petDog;
    */




}
