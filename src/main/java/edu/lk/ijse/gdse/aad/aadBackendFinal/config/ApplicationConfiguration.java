package edu.lk.ijse.gdse.aad.aadBackendFinal.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfiguration {





    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }







}
