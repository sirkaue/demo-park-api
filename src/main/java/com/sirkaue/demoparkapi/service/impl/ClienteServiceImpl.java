package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.exception.CpfUniqueViolationException;
import com.sirkaue.demoparkapi.exception.EntityNotFoundException;
import com.sirkaue.demoparkapi.exception.UsernameUniqueViolationException;
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
            throw new CpfUniqueViolationException(String.format("CPF '%s' não pode ser cadastrado, " +
                    "já existe no sistema.", cliente.getCpf()));
        }

        if (clienteRepository.existsByEmail(cliente.getUsuario().getUsername())) {
            String email = cliente.getUsuario().getUsername();
            throw new UsernameUniqueViolationException(String.format("Não foi possível realizar o cadastro, " +
                    "já existe um registro no sistema com o e-mail '%s'.", email));
        }
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema.", id)));
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
                () -> new EntityNotFoundException(String.format("Cliente com CPF '%s' não encontrado", cpf))
        );
    }
}
