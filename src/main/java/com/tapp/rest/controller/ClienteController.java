package com.tapp.rest.controller;

import com.tapp.domain.entitities.Cliente;
import com.tapp.domain.repositories.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clientes clientes;

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id ){
         Optional<Cliente> cliente = clientes.findById(id);
         if(cliente.isPresent()){
             return  ResponseEntity.ok(cliente.get());
         }else {
             return  ResponseEntity.notFound().build();
         }
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save (@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

}
