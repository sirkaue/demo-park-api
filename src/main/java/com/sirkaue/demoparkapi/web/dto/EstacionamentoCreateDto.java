package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class EstacionamentoCreateDto {

    @NotBlank
    @Size(min = 8, max = 8)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "A placa do veículo deve seguir o padrão 'AAA-0000'")
    private String placa;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    private String cor;

    @NotBlank
    @Size(min = 11, max = 11)
    @CPF
    private String clienteCpf;

    public EstacionamentoCreateDto() {
    }

    public EstacionamentoCreateDto(String placa, String marca, String modelo, String cor, String clienteCpf) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.clienteCpf = clienteCpf;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }
}
