package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;


import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(
                new ApiResponse(HttpStatus.OK.value(),
                        "User Found!",
                        userService.getUserById(userId))
        );
    }

    @PostMapping("/crateUser")
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponse(201, "Ok", userService.saveUser(userDTO)));
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "All users", userService.getAllUsers()));
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "User Deleted!", userService.deleteUser(userId)));
    }


    @PostMapping("/addImage/{userId}")
    public ResponseEntity<ApiResponse> addImage(@PathVariable Integer userId, @RequestParam("userImageFile") MultipartFile file) {

        System.out.println("userId = " + userId);
        System.out.println("fileName = " + file.getOriginalFilename());

        userService.uploadUserImage(userId, file);

        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Image Upload Successful!", null));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserDTO userDTO) {
        //call userService method here

//        System.out.println("Put Mapping: " + userDTO);

        userService.updateUser(userDTO);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "User Updated!", null));



    }

    //Delete User


}
