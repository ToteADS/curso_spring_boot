package com.tapp.service.impl;

import com.tapp.domain.repositories.Pedidos;
import com.tapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidosServiceImpl implements PedidoService {

    @Autowired
    private Pedidos repository;

}
