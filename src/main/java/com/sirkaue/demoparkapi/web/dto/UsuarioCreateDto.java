package com.sirkaue.demoparkapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioCreateDto {

    @NotBlank(message = "{NotBlank.usuarioCreateDto.username}")
    @Email(message = "{Email.usuarioCreateDto.username}", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String username;

    @NotBlank(message = "{NotBlank.usuarioCreateDto.password}")
    @Size(min = 6, max = 6, message = "{Size.usuarioCreateDto.password}")
    private String password;

    public UsuarioCreateDto() {
    }

    public UsuarioCreateDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioCreateDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
