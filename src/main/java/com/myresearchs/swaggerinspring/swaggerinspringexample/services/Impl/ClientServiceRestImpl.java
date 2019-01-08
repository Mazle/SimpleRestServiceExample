package com.myresearchs.swaggerinspring.swaggerinspringexample.services.Impl;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.ResponseClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import com.myresearchs.swaggerinspring.swaggerinspringexample.services.ClientMapper;
import com.myresearchs.swaggerinspring.swaggerinspringexample.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceRestImpl implements ClientService {

    private final ClientRepository repository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceRestImpl(ClientRepository repository, ClientMapper clientMapper) {
        this.repository = repository;
        this.clientMapper = clientMapper;
    }

    @Override
    public ResponseClientDTO addClient(RequestClientDTO request) {
        return clientMapper.toResponseClientDTO(repository.save(clientMapper.toClient(request)));
    }

    @Override
    public ResponseClientDTO updateClient(long id, RequestClientDTO requestClientDTO) {
        requestClientDTO.setId(id);
        return clientMapper.toResponseClientDTO(repository.save(clientMapper.toClient(requestClientDTO)));
    }

    @Override
    public List<ResponseClientDTO> getAllClients() {
        return clientMapper.toResponseClientDTO(repository.findAll());
    }

    @Override
    public ResponseClientDTO getClientById(long id) {
        return clientMapper.toResponseClientDTO(repository.findById(id).orElseThrow(NullPointerException::new));
    }

}
