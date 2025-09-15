package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pet_dog")
@ToString(exclude = {"owner", "images", "treatmentRequests"})

public class PetDog {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_id")
    private int dogId;


    @Column(name = "dog_name")
    private String dogName;


    @Column(name = "dog_breed")
    private String dogBreed;
    private int dogAge;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference("user-owner")
    private User owner;




    @OneToMany(mappedBy = "petDog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("pet-dog-images")
    private List<PetDogImage> images;


    @OneToMany(mappedBy = "petDog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference("owner-treatmentRequest")
    private List<TreatmentRequest> treatmentRequests;




}
