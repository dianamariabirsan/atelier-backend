package licenta.service;

import licenta.model.Client;
import licenta.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
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
    public Client update(Client client) {
        return repository.save(client);
    }

    @Override
    public void delete(Long clientId) {
        repository.deleteById(clientId);
    }
}
