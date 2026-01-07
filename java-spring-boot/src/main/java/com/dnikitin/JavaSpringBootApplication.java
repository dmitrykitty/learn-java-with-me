package com.dnikitin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

//each springboot project has this annotation
@SpringBootApplication
@RestController
public class JavaSpringBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JavaSpringBootApplication.class, args);
        Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        //beans - all the objects created during running the application

    }

    //this method is rest point
    @GetMapping
    public String helloWorld() {
        return "Hello World from Spring Boot!";
    }
}
