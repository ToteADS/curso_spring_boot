package com.tapp.service.impl;

import com.tapp.domain.entitities.Cliente;
import com.tapp.domain.entitities.ItemPedido;
import com.tapp.domain.entitities.Pedido;
import com.tapp.domain.entitities.Produto;
import com.tapp.domain.repositories.Clientes;
import com.tapp.domain.repositories.ItemsPedidos;
import com.tapp.domain.repositories.Pedidos;
import com.tapp.domain.repositories.Produtos;
import com.tapp.exception.RegraNegocioException;
import com.tapp.rest.dto.ItemPedidoDTO;
import com.tapp.rest.dto.PedidoDTO;
import com.tapp.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidosServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepositoory;
    private final ItemsPedidos itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(()-> new RegraNegocioException("Código de Cliente Inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);


        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido ,List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items.");
        }
        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepositoory.findById(idProduto).orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
