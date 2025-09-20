package edu.lk.ijse.gdse.aad.aadBackendFinal.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/debug")
public class DebugController {



//    @GetMapping("/testAuth")
    @PostMapping("/testAuth")
//    @PreAuthorize("hasRole('PET_OWNER')")
    public String testAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Authorities: " + auth.getAuthorities());
        System.out.println("Principal: " + auth.getPrincipal());
        return "OK";
    }




}
