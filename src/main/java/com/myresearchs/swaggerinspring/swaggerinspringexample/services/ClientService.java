package com.myresearchs.swaggerinspring.swaggerinspringexample.services;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.ResponseClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {
    ResponseClientDTO addClient(RequestClientDTO request);
    ResponseClientDTO updateClient(long id, RequestClientDTO requestClientDTO);
    List<ResponseClientDTO> getAllClients();
    ResponseClientDTO getClientById(long id);

}
