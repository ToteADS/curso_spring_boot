package com.tapp.domain.repositories;

import com.tapp.domain.entitities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
}
