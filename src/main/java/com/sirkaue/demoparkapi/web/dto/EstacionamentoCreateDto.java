package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class EstacionamentoCreateDto {

    @NotBlank(message = "{NotBlank.estacionamentoCreateDto.placa}")
    @Size(message = "{Size.estacionamentoCreateDto.placa}", min = 8, max = 8)
    @Pattern(regexp = "[A-Z]{3}-[0-9]{4}", message = "{Pattern.estacionamentoCreateDto.placa}")
    private String placa;

    @NotBlank(message = "{NotBlank.estacionamentoCreateDto.marca}")
    private String marca;

    @NotBlank(message = "{NotBlank.estacionamentoCreateDto.modelo}")
    private String modelo;

    @NotBlank(message = "{NotBlank.estacionamentoCreateDto.cor}")
    private String cor;

    @NotBlank(message = "{NotBlank.estacionamentoCreateDto.clienteCpf}")
    @Size(message = "{Size.estacionamentoCreateDto.clienteCpf}", min = 11, max = 11)
    @CPF(message = "{CPF.estacionamentoCreateDto.clienteCpf}")
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

    private EstacionamentoCreateDto(Builder builder) {
        this.placa = builder.placa;
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.cor = builder.cor;
        this.clienteCpf = builder.clienteCpf;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String placa;
        private String marca;
        private String modelo;
        private String cor;
        private String clienteCpf;

        public Builder placa(String placa) {
            this.placa = placa;
            return this;
        }

        public Builder marca(String marca) {
            this.marca = marca;
            return this;
        }

        public Builder modelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public Builder cor(String cor) {
            this.cor = cor;
            return this;
        }

        public Builder clienteCpf(String clienteCpf) {
            this.clienteCpf = clienteCpf;
            return this;
        }

        public EstacionamentoCreateDto build() {
            return new EstacionamentoCreateDto(this);
        }
    }
}
