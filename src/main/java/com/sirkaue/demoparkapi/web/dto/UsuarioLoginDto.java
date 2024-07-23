package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {

    @NotBlank
    @Email(message = "formato do e-mail inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank
    @Size(min = 6, max = 6)
    private String password;

    public UsuarioLoginDto() {
    }

    public UsuarioLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
