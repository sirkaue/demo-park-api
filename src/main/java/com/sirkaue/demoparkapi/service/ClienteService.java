package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.exception.CpfUniqueViolationException;
import com.sirkaue.demoparkapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
           return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException e) {
            throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, já existe" +
                    " no sistema", cliente.getCpf()));
        }
    }
}
