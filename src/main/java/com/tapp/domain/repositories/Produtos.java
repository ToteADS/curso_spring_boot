package com.tapp.domain.repositories;

import com.tapp.domain.entitities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
