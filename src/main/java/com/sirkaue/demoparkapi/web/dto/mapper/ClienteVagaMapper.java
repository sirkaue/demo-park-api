package com.sirkaue.demoparkapi.web.dto.mapper;

//import com.sirkaue.demoparkapi.entity.Cliente;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoCreateDto;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoResponseDto;
import org.modelmapper.ModelMapper;

public class ClienteVagaMapper {

    private ClienteVagaMapper() {
    }

    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto) {
        return new ModelMapper().map(dto, ClienteVaga.class);
    }

// Forma manual de se fazer 'mapeamento de propriedade aninhada (encadeamento)'.

//    public static ClienteVaga toClienteVaga(EstacionamentoCreateDto dto) {
//        ClienteVaga clienteVaga = new ClienteVaga();
//        Cliente cliente = new Cliente();
//
//        clienteVaga.setPlaca(dto.getPlaca());
//        clienteVaga.setMarca(dto.getMarca());
//        clienteVaga.setModelo(dto.getModelo());
//        clienteVaga.setCor(dto.getCor());
//        cliente.setCpf(dto.getClienteCpf());
//        clienteVaga.setCliente(cliente);
//
//        return clienteVaga;
//    }

    public static EstacionamentoResponseDto toDto(ClienteVaga clienteVaga) {
        return new ModelMapper().map(clienteVaga, EstacionamentoResponseDto.class);
    }
}
