package licenta.service;

import licenta.model.Client;

import java.util.List;

public interface ClientService {
    Client save(Client client);

    List<Client> getClients();

    Client getClientById(long id);

    Client update(Client client);

    void delete(Long clientId);
}
