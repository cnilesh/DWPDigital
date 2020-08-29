package com.dwp.location.app;

import com.dwp.location.client.Client;
import com.dwp.location.controller.LocationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.dwp.location"})
public class LocationApp {
    public static void main(String[] args) {
        SpringApplication.run(LocationApp.class, args);
    }
}
