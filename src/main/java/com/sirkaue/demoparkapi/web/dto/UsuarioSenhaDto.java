package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioSenhaDto {

    @NotBlank(message = "{NotBlank.usuarioSenhaDto.senhaAtual}")
    @Size(message = "{Size.usuarioSenhaDto.senhaAtual}", min = 6, max = 6)
    private String senhaAtual;

    @NotBlank(message = "{NotBlank.usuarioSenhaDto.novaSenha}")
    @Size(message = "{Size.usuarioSenhaDto.novaSenha}", min = 6, max = 6)
    private String novaSenha;

    @NotBlank(message = "{NotBlank.usuarioSenhaDto.confirmaSenha}")
    @Size(message = "{Size.usuarioSenhaDto.confirmaSenha}", min = 6, max = 6)
    private String confirmaSenha;

    public UsuarioSenhaDto() {
    }

    public UsuarioSenhaDto(String senhaAtual, String novaSenha, String confirmaSenha) {
        this.senhaAtual = senhaAtual;
        this.novaSenha = novaSenha;
        this.confirmaSenha = confirmaSenha;
    }

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    @Override
    public String toString() {
        return "UsuarioSenhaDto{" +
                "senhaAtual='" + senhaAtual + '\'' +
                ", novaSenha='" + novaSenha + '\'' +
                ", confirmaSenha='" + confirmaSenha + '\'' +
                '}';
    }
}
