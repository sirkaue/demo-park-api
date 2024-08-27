package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.repository.projection.ClienteVagaProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteVagaService {

    ClienteVaga salvar(ClienteVaga clienteVaga);

    ClienteVaga buscarPorRecibo(String recibo);

    long getTotalDeVezesEstacionamentoCompleto(String cpf);

    Page<ClienteVagaProjection> buscarTodosPorClienteCpf(String cpf, Pageable pageable);

    Page<ClienteVagaProjection> buscarTodosPorUsuarioId(Long id, Pageable pageable);
}
