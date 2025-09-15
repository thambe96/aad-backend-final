package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pet_dog_image")

public class PetDogImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_dog_image_id")
    private int petDogImageId;


    @Column(name = "pet_dog_image_url")
    private String petDogImageUrl;


    @Column(name = "public_id")
    private String publicId;


    @ManyToOne
    @JoinColumn(name = "dog_id")
    private PetDog petDog;

}
