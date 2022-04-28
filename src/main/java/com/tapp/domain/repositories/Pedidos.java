package com.tapp.domain.repositories;

import com.tapp.domain.entitities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Pedidos extends JpaRepository<Pedido, Integer> {
  //  List<Pedido> findByCliente();
}
