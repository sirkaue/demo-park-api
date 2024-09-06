package com.sirkaue.demoparkapi.service;

public interface JasperService {

    void addParams(String key, Object value);
    byte[] gerarPdf();
}
