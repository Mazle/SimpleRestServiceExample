package com.myresearchs.swaggerinspring.swaggerinspringexample.services;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {
    Client addClient(Client client);
    Client updateClient(long id, Client client);
    List<Client> getAllClients();
    Client getClientById(long id);

}
