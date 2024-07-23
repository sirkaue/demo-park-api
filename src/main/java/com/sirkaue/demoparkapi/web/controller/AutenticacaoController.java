package com.sirkaue.demoparkapi.web.controller;

import com.sirkaue.demoparkapi.jwt.JwtToken;
import com.sirkaue.demoparkapi.jwt.JwtUserDetailsService;
import com.sirkaue.demoparkapi.web.dto.UsuarioLoginDto;
import com.sirkaue.demoparkapi.web.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AutenticacaoController {

    private static final Logger log = LoggerFactory.getLogger(AutenticacaoController.class);

    @Autowired
    private JwtUserDetailsService detailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> autenticar(@RequestBody @Valid UsuarioLoginDto dto, HttpServletRequest request) {

        log.info("Processo de autenticação pelo login {}", dto.getUsername());

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

            authenticationManager.authenticate(authenticationToken);
            JwtToken token = detailsService.getTokenAuthenticated(dto.getUsername());

            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            log.warn("Bad Credentials from username {}", dto.getUsername());
        }
        return ResponseEntity
                .badRequest()
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, "Credenciais Inválidas."));
    }
}
