package edu.lk.ijse.gdse.aad.aadBackendFinal.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.lk.ijse.gdse.aad.aadBackendFinal.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    private final UserRepo userRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }




    @Bean
    public UserDetailsService userDetailsService() {
        return username ->
                userRepository.findByName(username)
                        .map(user ->
                                new org.springframework.security
                                        .core.userdetails.User(
                                        user.getName(),
                                        user.getPassword(),
                                        List.of(new SimpleGrantedAuthority
                                                ("ROLE_"+user.getRole()
                                                        .name()))
                                )).orElseThrow(
                                ()->new UsernameNotFoundException
                                        ("User not found")
                        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }







}
