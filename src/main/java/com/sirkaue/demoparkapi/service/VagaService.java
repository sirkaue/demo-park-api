package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.Vaga;

public interface VagaService {

    Vaga salvar(Vaga vaga);

    Vaga buscarPorCodigo(String codigo);

    Vaga buscarPorVagaLivre();
}
