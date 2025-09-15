package edu.lk.ijse.gdse.aad.aadBackendFinal.dto;

import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.PetDog;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Role;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.UserImage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@AllArgsConstructor
@Data

public class UserDTO {

    private int id;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String address;
    private String email;

    private UserImage userImage;

    private Role role;

    private List<PetDog> dogs;



}
