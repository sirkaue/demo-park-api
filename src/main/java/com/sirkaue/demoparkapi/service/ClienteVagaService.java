package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.repository.ClienteVagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteVagaService {

    @Autowired
    private ClienteVagaRepository repository;

    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga) {
        return repository.save(clienteVaga);
    }
}
