package com.ubb.licenta.converter;

import com.ubb.licenta.dto.ClientDto;
import com.ubb.licenta.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {

    private static final Logger log = LoggerFactory.getLogger(ClientConverter.class);
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto = new ClientDto(client.getName(),
                client.getEmail(), client.getPhoneNumber());
        clientDto.setId(client.getId());
        return clientDto;
    }
}
