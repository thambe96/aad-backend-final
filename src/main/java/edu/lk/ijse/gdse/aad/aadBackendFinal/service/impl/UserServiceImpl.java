package edu.lk.ijse.gdse.aad.aadBackendFinal.service.impl;

import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.User;
import edu.lk.ijse.gdse.aad.aadBackendFinal.entity.UserImage;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.EmptyResourceException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceAlreadyExistException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.exception.ResourceNotFoundException;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.UserRepo;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.CloudinaryService;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;

    @Transactional
    @Override
    public String saveUser(UserDTO userDTO) {

        int userId = userDTO.getId();

        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            throw new ResourceAlreadyExistException("User Already Exists");
        }


        userRepo.save(modelMapper.map(userDTO, User.class));

        return "User Saved Successfully";
    }

    @Override
    public UserDTO getUserById(int id) {

        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }

        UserDTO  userDTO = new UserDTO(
                user.get().getId(),
                user.get().getName(),
                user.get().getPassword(),
                user.get().getAge(),
                user.get().getGender(),
                user.get().getAddress(),
                user.get().getEmail(),
                user.get().getUserImage(),
                user.get().getRole(),
                user.get().getDogs()
        );


        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = userRepo.findAll();

        if (users.isEmpty()) {
            throw new EmptyResourceException("User list is empty");
        } else {
            List<UserDTO> userDTOs = new ArrayList<>();
            for (User user : users) {
                UserDTO userDTO = new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getPassword(),
                        user.getAge(),
                        user.getGender(),
                        user.getAddress(),
                        user.getEmail(),
                        user.getUserImage(),
                        user.getRole(),
                        user.getDogs()
                );
                userDTOs.add(userDTO);
            }

            return userDTOs;

        }

    }

    @Override
    public String deleteUser(int userId) {

        User user = userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        userRepo.deleteById(userId);
        return "User Deleted Successfully";
    }

    @Override
    public String uploadUserImage(int userId, MultipartFile file) {

        User user = userRepo.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "User with id " + userId + " not found")
        );


        try {

            Map uploadResult = cloudinaryService.uploadFile(file);
            String imageUrl = uploadResult.get("secure_url").toString();
            String publicId = uploadResult.get("public_id").toString();

            UserImage userImage = new UserImage();
            userImage.setImageUrl(imageUrl);
            userImage.setPublicId(publicId);

            //first
            user.setUserImage(userImage);

            //second
            userImage.setUser(user);


            userRepo.save(user);


            return "Image Uploaded Successfully!";

        } catch (IOException e) {
            throw new RuntimeException(e); // handle this properly in the global exception handler
        }
    }

    @Override
    public String updateUser(UserDTO userDTO) {

        User user = userRepo.findById(userDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id " + userDTO.getId() + " not found"));

        userRepo.save(modelMapper.map(userDTO, User.class));

        return "User Updated Successfully!";
    }


}
