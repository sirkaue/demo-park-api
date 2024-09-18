package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.exception.*;
import com.sirkaue.demoparkapi.repository.ClienteRepository;
import com.sirkaue.demoparkapi.repository.projection.ClienteProjection;
import com.sirkaue.demoparkapi.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new CpfUniqueViolationException("CPF", cliente.getCpf());
        }

        if (clienteRepository.existsByEmail(cliente.getUsuario().getUsername())) {
            String email = cliente.getUsuario().getUsername();
            throw new UsernameUniqueViolationException("e-mail", email);
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new ClienteNotFoundException(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClienteProjection> buscarTodos(Pageable pageable) {
        return clienteRepository.findAllPageable(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorUsuarioId(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new ClienteCpfNotFoundException("CPF", cpf));
    }
}
