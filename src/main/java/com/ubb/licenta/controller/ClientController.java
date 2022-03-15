package com.ubb.licenta.controller;

import com.ubb.licenta.converter.BaseConverter;
import com.ubb.licenta.dto.ClientDto;
import com.ubb.licenta.model.Client;
import com.ubb.licenta.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/client") //localhost:8080/client
@Slf4j
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private BaseConverter<Client, ClientDto> converter;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("clientId") Integer clientId) {
        //localhost:8080/client/1
        Client client = service.getClientById(clientId);
        ClientDto clientDto = converter.convertModelToDto(client);
        if (clientDto == null) {
            log.info("Unable to find any client with id: " + clientId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Returning client with id: " + clientId);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping("/clients")
    //localhost:8080/client/clients
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<Client> clients = service.getClients();
        List<ClientDto> clientsDto = converter.convertModelsToDtos(clients);
        if (clientsDto == null) {
            log.info("Unable to find any clients");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> save(@RequestBody ClientDto clientDto) {
        Client client = converter.convertDtoToModel(clientDto);
        Client saved = service.save(client);
        if (saved == null) {
            log.info("Unable to save client");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientDto> update(@RequestBody ClientDto clientDto) {
        Client client = converter.convertDtoToModel(clientDto);
        Client saved = service.update(client);
        if (saved == null) {
            log.info("Unable to update client");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Saved" + saved);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }
}