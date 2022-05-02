package com.tapp.rest.dto;

import com.tapp.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;
    @NotNull(message = "Campo Total do pedido é obrigatorio")
    private BigDecimal total;
    @NotEmptyList(message = "Pedido não pode ser realizado sem itens")
    private List<ItemPedidoDTO> items;
}
