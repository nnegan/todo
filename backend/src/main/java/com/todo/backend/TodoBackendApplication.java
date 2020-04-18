package com.todo.backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan( basePackages = {"com.todo"} )
public class TodoBackendApplication {

    public static void main(String[] args) {
      //  String profile = "local";
        System.setProperty("spring.profiles.active", "local");
        SpringApplication.run(TodoBackendApplication.class, args);
    }
}
