package com.sirkaue.demoparkapi.service;

import com.sirkaue.demoparkapi.entity.ClienteVaga;

public interface EstacionamentoService {

    ClienteVaga checkIn(ClienteVaga clienteVaga);

    ClienteVaga checkOut(String recibo);
}
