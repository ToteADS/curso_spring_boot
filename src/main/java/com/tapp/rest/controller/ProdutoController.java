package com.tapp.rest.controller;

import com.tapp.domain.entitities.Produto;
import com.tapp.domain.repositories.Pedidos;
import com.tapp.domain.repositories.Produtos;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private Produtos repository;

    @GetMapping("{id}")
    public Produto findProdutoById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
        return repository.save(produto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id).map(
                produto -> {
                    repository.delete(produto);
                    return null;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        repository.findById(id)
                .map(produtoExistente -> {
                            produto.setId(produtoExistente.getId());
                            repository.save(produto);
                            return produto;
                        }
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }


}
