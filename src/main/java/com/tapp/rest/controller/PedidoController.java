package com.tapp.rest.controller;

import com.tapp.domain.entitities.ItemPedido;
import com.tapp.domain.entitities.Pedido;
import com.tapp.domain.enums.StatusPedido;
import com.tapp.rest.dto.AtualizacaoStatusPedidoDTO;
import com.tapp.rest.dto.InformacaoItemPedidoDTO;
import com.tapp.rest.dto.InformacoesPedidoDTO;
import com.tapp.rest.dto.PedidoDTO;
import com.tapp.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado"));
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));

    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItens()))
                .build();

    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return  itens.stream().map(
                itemPedido -> InformacaoItemPedidoDTO
                    .builder()
                        .descricaoProduto(itemPedido.getProduto().getDescricao())
                        .precoUnitario(itemPedido.getProduto().getPreco())
                        .quantidade(itemPedido.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
