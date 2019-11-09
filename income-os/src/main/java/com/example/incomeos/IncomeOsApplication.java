package com.example.incomeos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class IncomeOsApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncomeOsApplication.class, args);
    }

}
