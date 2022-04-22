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

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Tote"));
            clientes.save(new Cliente("Nalita"));
            clientes.save(new Cliente("Ana Clara"));

            boolean existe = clientes.existsByNome("Dougllas");
            System.out.println("existe um cliente com o nome Dougllas? " + existe);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
