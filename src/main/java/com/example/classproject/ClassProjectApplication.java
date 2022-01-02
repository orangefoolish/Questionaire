package com.example.classproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class ClassProjectApplication {
    public static void main(String[] args) {
        try{
            SpringApplication.run(ClassProjectApplication.class, args);
        }catch (Exception e){
            return ;
        }
    }

}
