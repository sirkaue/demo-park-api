package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.exception.EntityNotFoundException;
import com.sirkaue.demoparkapi.exception.ReciboNotFoundException;
import com.sirkaue.demoparkapi.repository.ClienteVagaRepository;
import com.sirkaue.demoparkapi.repository.projection.ClienteVagaProjection;
import com.sirkaue.demoparkapi.service.ClienteVagaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteVagaServiceImpl implements ClienteVagaService {

    private final ClienteVagaRepository repository;

    public ClienteVagaServiceImpl(ClienteVagaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga) {
        return repository.save(clienteVaga);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteVaga buscarPorRecibo(String recibo) {
        return repository.findByReciboAndDataSaidaIsNull(recibo).orElseThrow(
                () -> new ReciboNotFoundException(recibo));
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalDeVezesEstacionamentoCompleto(String cpf) {
        return repository.countByClienteCpfAndDataSaidaIsNotNull(cpf);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClienteVagaProjection> buscarTodosPorClienteCpf(String cpf, Pageable pageable) {
        return repository.findAllByClienteCpf(cpf, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClienteVagaProjection> buscarTodosPorUsuarioId(Long id, Pageable pageable) {
        return repository.findAllByClienteUsuarioId(id, pageable);
    }
}
