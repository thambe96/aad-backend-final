package edu.lk.ijse.gdse.aad.aadBackendFinal.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
@Builder
public class User {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String address;
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_image_id", referencedColumnName = "id")
    @JsonBackReference("user-image")
    @ToString.Exclude
    private UserImage userImage;




    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference
    private List<PetDog> dogs;

    @OneToMany(mappedBy = "donator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Donation> donations;





}
