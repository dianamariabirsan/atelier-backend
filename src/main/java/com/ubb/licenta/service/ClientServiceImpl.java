package com.ubb.licenta.service;

import com.ubb.licenta.repository.ClientRepository;
import com.ubb.licenta.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository repository;

    @Override
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public List<Client> getClients() {
        return repository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        return repository.findClientById(id);
    }

    @Override
    public Client update(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Long clientId) {
        repository.deleteById(clientId);
    }
}
