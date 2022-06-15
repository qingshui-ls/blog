package com.ecust.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BlogApApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(BlogApApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
