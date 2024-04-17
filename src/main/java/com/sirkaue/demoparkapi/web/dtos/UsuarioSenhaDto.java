package com.sirkaue.demoparkapi.web.dtos;

public class UsuarioSenhaDto {
    private String senhaAtual;
    private String novaSenha;
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
