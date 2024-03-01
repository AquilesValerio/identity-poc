package br.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com"})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


}