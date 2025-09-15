package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    String saveUser(UserDTO userDTO);
    UserDTO getUserById(int id);

    List<UserDTO> getAllUsers();
    String deleteUser(int userId);

    String uploadUserImage(int userId, MultipartFile file);

    String updateUser(UserDTO userDTO);


}
