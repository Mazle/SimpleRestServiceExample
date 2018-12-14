package com.myresearchs.swaggerinspring.swaggerinspringexample.controllers;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//Нужно ли нам возвращать ResponseEntity?
@Api(value = "Client Rest Controller", description = "REST APIs for Client entity.")
@RestController
@RequestMapping("/client")
public class ClientRestController {

    private ClientService service;
    @ApiOperation(value = "Add new Client in base", response = Client.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client addClient(@RequestBody Client client){
        return service.addClient(client);
    }

    @ApiOperation(value = "Update client with specified id", response = Client.class)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client updateClient(@PathVariable("id") long id, @RequestBody Client client){
        return service.updateClient(id,client);
    }

    @ApiOperation(value = "Return List of all clients from base", response = Iterable.class)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAllClients(){
        return service.getAllClients();
    }

    @ApiOperation(value = "Get client with specified id from base", response = Client.class)
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client getClient(@PathVariable("id") long id){
        return service.getClientById(id);
    }

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }
}
