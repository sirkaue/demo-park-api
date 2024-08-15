package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.entity.Vaga;
import com.sirkaue.demoparkapi.util.EstacionamentoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EstacionamentoService {

    @Autowired
    private ClienteVagaService clienteVagaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VagaService vagaService;

    @Transactional
    public ClienteVaga checkIn(ClienteVaga clienteVaga) {
        Cliente cliente = clienteService.buscarPorCpf(clienteVaga.getCliente().getCpf());
        clienteVaga.setCliente(cliente);

        Vaga vaga = vagaService.buscarPorVagaLivre();
        vaga.setStatus(Vaga.StatusVaga.OCUPADA);
        clienteVaga.setVaga(vaga);
        clienteVaga.setDataEntrada(LocalDateTime.now());
        clienteVaga.setRecibo(EstacionamentoUtils.gerarRecibo());
        return clienteVagaService.salvar(clienteVaga);
    }
}
