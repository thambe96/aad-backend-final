package edu.lk.ijse.gdse.aad.aadBackendFinal.service;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.AuthDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.AuthResponseDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;

public interface AuthService {

    AuthResponseDTO authenticate(AuthDTO authDTO);

    String register(UserDTO userDTO);




}
