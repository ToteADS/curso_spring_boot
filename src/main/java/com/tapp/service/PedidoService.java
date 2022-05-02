 package com.tapp.service;

 import com.tapp.domain.entitities.Pedido;
 import com.tapp.rest.dto.PedidoDTO;

 import java.util.Optional;

 public interface PedidoService {
    Pedido salvar (PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
