package com.tapp;

import com.tapp.domain.entitity.Cliente;
import com.tapp.domain.repositories.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

           System.out.println("Atualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientes.save(c);
            });
            todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando Clientes");
            clientes.findByNomeLike("An").forEach(System.out::println);


            System.out.println("Deletando Clientes");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });


            todosClientes = clientes.findAll();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum Cliente Encontrado");
            }else{
                todosClientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
