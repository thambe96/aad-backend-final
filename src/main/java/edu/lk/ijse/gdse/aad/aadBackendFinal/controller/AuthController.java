package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;


import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.ApiResponse;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.AuthDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.dto.UserDTO;
import edu.lk.ijse.gdse.aad.aadBackendFinal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {


    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(
            @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.register(userDTO)));
    }



    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @RequestBody AuthDTO authDTO) {

        System.out.println("request for registration");


        return ResponseEntity.ok(new ApiResponse(
                200,
                "OK",
                authService.authenticate(authDTO)));
    }



}
