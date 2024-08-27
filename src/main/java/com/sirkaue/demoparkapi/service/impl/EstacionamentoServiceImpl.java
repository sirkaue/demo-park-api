package com.sirkaue.demoparkapi.service.impl;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.entity.Vaga;
import com.sirkaue.demoparkapi.service.ClienteService;
import com.sirkaue.demoparkapi.service.ClienteVagaService;
import com.sirkaue.demoparkapi.service.EstacionamentoService;
import com.sirkaue.demoparkapi.service.VagaService;
import com.sirkaue.demoparkapi.util.EstacionamentoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class EstacionamentoServiceImpl implements EstacionamentoService {

    private final ClienteVagaService clienteVagaService;

    private final ClienteService clienteService;

    private final VagaService vagaService;

    public EstacionamentoServiceImpl(
            ClienteVagaService clienteVagaService,
            ClienteService clienteService,
            VagaService vagaService) {
        this.clienteVagaService = clienteVagaService;
        this.clienteService = clienteService;
        this.vagaService = vagaService;
    }

    @Override
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

    @Override
    @Transactional
    public ClienteVaga checkOut(String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        LocalDateTime dataSaida = LocalDateTime.now();
        LocalDateTime dataEntrada = clienteVaga.getDataEntrada();
        BigDecimal valor = EstacionamentoUtils.calcularCusto(dataEntrada, dataSaida);
        clienteVaga.setValor(valor);

        long totalDeVezes = clienteVagaService.getTotalDeVezesEstacionamentoCompleto(clienteVaga.getCliente().getCpf());

        BigDecimal desconto = EstacionamentoUtils.calcularDesconto(valor, totalDeVezes);
        clienteVaga.setDesconto(desconto);

        clienteVaga.setDataSaida(dataSaida);
        clienteVaga.getVaga().setStatus(Vaga.StatusVaga.LIVRE);

        return clienteVagaService.salvar(clienteVaga);
    }
}
