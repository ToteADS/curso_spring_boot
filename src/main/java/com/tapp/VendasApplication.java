package com.tapp;

import com.tapp.domain.entitities.Cliente;
import com.tapp.domain.entitities.Pedido;
import com.tapp.domain.repositories.Clientes;
import com.tapp.domain.repositories.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.save(new Cliente("Tote"));
            clientes.save(new Cliente("Nalita"));
            clientes.save(new Cliente("Ana Clara"));

            Cliente tote = clientes.getOne(1);
            Pedido p = new Pedido();
            p.setCliente(tote);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            Cliente cliente = clientes.findClienteFetchPedidos(tote.getId());

            System.out.println(cliente);
            System.out.println(cliente.getPedidos());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
