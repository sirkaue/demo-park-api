package com.sirkaue.demoparkapi.web.dto.mapper;

import com.sirkaue.demoparkapi.entity.Cliente;
import com.sirkaue.demoparkapi.web.dto.ClienteCreateDto;
import com.sirkaue.demoparkapi.web.dto.ClienteResponseDto;
import org.modelmapper.ModelMapper;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente toCliente(ClienteCreateDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ModelMapper().map(cliente, ClienteResponseDto.class);
    }
}
