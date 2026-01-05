package com.dnikitin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//each springboot project has this annotation
@SpringBootApplication
@RestController
public class JavaSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSpringBootApplication.class, args);
    }

    //this method is rest point
    @GetMapping
    public String helloWorld() {
        return "Hello World from Spring Boot!";
    }

}
