package com.tapp.domain.repositories;
import com.tapp.domain.entitity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository<Cliente,Integer> {

    List<Cliente> findByNomeContaining(String nome);

    boolean existsByNome(String nome);
}
