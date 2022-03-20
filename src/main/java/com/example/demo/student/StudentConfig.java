package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student Juan = new Student("Juan","JuanEmail",LocalDate.of(2002, Month.OCTOBER,5));
            Student Maria= new Student("Alex","AlexEmail",LocalDate.of(1993, Month.FEBRUARY,25));
            repository.saveAll(List.of(Juan,Maria));
        };

    }
}
