package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.AuthDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.AuthResponseDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.Role;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.UserRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.AuthService;
import edu.lk.ijse.gdse.aad.aadBackendFinal.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;



    @Override
    public AuthResponseDTO authenticate(AuthDTO authDTO) {

        User user=
                userRepository.findByName(authDTO.getName())
                        .orElseThrow(
                                ()->new UsernameNotFoundException
                                        ("Username not found"));
        if (!passwordEncoder.matches(
                authDTO.getPassword(),
                user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }
        String token=jwtUtil.generateToken(authDTO.getName());
        return  new AuthResponseDTO(token);



    }

    @Override
    public String register(UserDTO userDTO) {
        if(userRepository.findByName(
                userDTO.getName()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

/*

        User user=User.builder()
                .name(userDTO.getName())
                .password(passwordEncoder.encode(
                        userDTO.getPassword()))
                .role(userDTO.getRole())
                .build();

        */




        userRepository.save(modelMapper.map(userDTO, User.class));
        return  "User Registration Success";
    }
}
