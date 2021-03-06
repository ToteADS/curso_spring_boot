package com.tapp;

import com.tapp.domain.entitities.Cliente;
import com.tapp.domain.repositories.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {



    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
