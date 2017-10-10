package com.uber.coding_challange.food_trucks_web_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uber.coding_challange.food_trucks_web_service.ctrl.FoodTruckClient;

@SpringBootApplication
public class Main {

    public static void main(String[] args) 
    {
        SpringApplication.run(Main.class, args);
        FoodTruckClient.getInstance().initialize();
    }
}
