package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteCreateDto {

    @NotBlank
    @Size(min = 5, max = 100)
    private String nome;

    @CPF
    @Size(min = 11, max = 11)
    private String cpf;

    public ClienteCreateDto() {
    }

    public ClienteCreateDto(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
