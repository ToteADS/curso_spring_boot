package com.tapp.service;

import com.tapp.model.Cliente;
import com.tapp.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {
    @Autowired
    private ClientesRepository clientesRepository;
    public void salvarClientes(Cliente cliente){
        validarCliente(cliente);
        clientesRepository.persistir(cliente);
    }
    public void validarCliente(Cliente cliente){

    }
}
