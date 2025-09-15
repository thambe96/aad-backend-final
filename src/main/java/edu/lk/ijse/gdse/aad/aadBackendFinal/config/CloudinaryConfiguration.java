package edu.lk.ijse.gdse.aad.aadBackendFinal.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {


    @Value("{cloudinary.cloud_name}")
    private String cloudName;


    @Value("{cloudinary.api_key}")
    private String apiKey;


    @Value("{cloudinary.api_secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dk0c1qe0x",
                "api_key", "913212745155833",
                "api_secret", "F4cZgsXvLxVWcTP8no9WeWx5qrE"
        ));
    }



}
