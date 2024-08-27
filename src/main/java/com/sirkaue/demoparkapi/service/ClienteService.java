package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.repository.projection.ClienteProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClienteService {

    Cliente salvar(Cliente cliente);

    Cliente buscarPorId(Long id);

    Page<ClienteProjection> buscarTodos(Pageable pageable);

    Cliente buscarPorUsuarioId(Long id);

    Cliente buscarPorCpf(String cpf);
}
