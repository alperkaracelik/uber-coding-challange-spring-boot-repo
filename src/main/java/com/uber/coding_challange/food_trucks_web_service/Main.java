package com.uber.coding_challange.food_trucks_web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.uber.coding_challange.food_trucks_web_service.ctrl.FoodTruckClient;

@SpringBootApplication
public class Main {

    public static void main(String[] args) 
    {
        SpringApplication.run(Main.class, args);
        FoodTruckClient.getInstance().initialize();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() 
    {
        return new WebMvcConfigurerAdapter() 
        {
            @Override
            public void addCorsMappings(CorsRegistry registry) 
            {
                registry.addMapping("/").allowedOrigins("https://foodtruckradar.herokuapp.com/");
                registry.addMapping("/query").allowedOrigins("https://foodtruckradar.herokuapp.com/");
            }
        };
    }
}
