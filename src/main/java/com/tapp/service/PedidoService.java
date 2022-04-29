 package com.tapp.service;

 import com.tapp.domain.entitities.Pedido;
 import com.tapp.rest.dto.PedidoDTO;

 public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
