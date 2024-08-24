package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.entity.Vaga;
import com.sirkaue.demoparkapi.util.EstacionamentoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
