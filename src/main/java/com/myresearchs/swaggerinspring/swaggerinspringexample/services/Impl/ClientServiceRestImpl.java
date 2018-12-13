package com.myresearchs.swaggerinspring.swaggerinspringexample.services.Impl;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import com.myresearchs.swaggerinspring.swaggerinspringexample.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceRestImpl implements ClientService {

    private ClientRepository repository;

    @Override
    public Client addClient(Client client) {
        return repository.save(client);
    }

    @Override
    public Client updateClient(long id, Client client) {
        client.setId(id);
        return repository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    public Client getClientById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Autowired
    public void setRepository(ClientRepository repository) {
        this.repository = repository;
    }

}
