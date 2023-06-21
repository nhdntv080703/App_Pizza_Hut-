package com.example.demo.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigCloudinary {
    @Bean
    Cloudinary cloudinary(){
        Map<String , String> config = new HashMap<>();
        config.put("api_key" , "578777557941588");
        config.put("api_secret" , "cExIEsVB7baAfXgpH9DD_JfH990");
        config.put("cloud_name" , "dqcnck5uw");
        return new Cloudinary(config);
    }
}
