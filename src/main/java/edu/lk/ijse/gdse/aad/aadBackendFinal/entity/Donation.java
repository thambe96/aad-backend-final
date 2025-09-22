package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "donation")
public class Donation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private int donationId;

    private long amount;

    private LocalDate date;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }


    @ManyToOne
    @JoinColumn(name = "donator_id")
    @JsonBackReference("user-sponsor")
    @ToString.Exclude
    private User donator;



    @ManyToOne
    @JoinColumn(name = "treatment_request_id")
    @JsonBackReference("owner-treatmentreq")
    @ToString.Exclude
    private TreatmentRequest treatmentRequest;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonBackReference
    @ToString.Exclude
    private Payment payment;




}
