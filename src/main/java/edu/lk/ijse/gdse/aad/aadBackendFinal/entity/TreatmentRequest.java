package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "treatment_request")
public class TreatmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "request_name")
    private String treatmentName;

    @Column(name = "treatment_description")
    private String treatmentDescription;

    @Column(name = "treatment_price")
    private double treatmentPrice;

    @Column(name = "collected_amount")
    private double collectedAmount;

    @Column(name = "request_status")
    @Enumerated(EnumType.STRING)
    private TreatmentReqStatus requestStatus;

    @ManyToOne
    @JoinColumn(name = "dog_id")
    @JsonBackReference("treatmentReq-petDog")
    private PetDog petDog;


    @OneToMany(mappedBy = "treatmentRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("treatmentReq-donation")
    private List<Donation> donations;


    @OneToMany(mappedBy = "treatmentRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("treatmentReq-health-record")
    private List<HealthRecord> healthRecords;



}
