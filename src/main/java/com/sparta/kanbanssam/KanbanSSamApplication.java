package com.sparta.kanbanssam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class KanbanSSamApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanSSamApplication.class, args);
    }

}
