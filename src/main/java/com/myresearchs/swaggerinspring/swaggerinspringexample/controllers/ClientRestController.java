package com.myresearchs.swaggerinspring.swaggerinspringexample.controllers;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.ResponseClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//Нужно ли нам возвращать ResponseEntity?
@Api(value = "Client Rest Controller", description = "REST APIs for Client entity.")
@RestController
@RequestMapping("/client")
public class ClientRestController {

    private final ClientService service;

    @Autowired
    public ClientRestController(ClientService service) {
        this.service = service;
    }

    @ApiOperation(value = "Add new Client in base")
    @PostMapping()
    public Client addClient(@RequestBody RequestClientDTO clientDto){
        return service.addClient(new Client(clientDto.getName(),clientDto.getDescription()));
    }

    @ApiOperation(value = "Update client with specified id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Client updateClient(@PathVariable("id") long id, @RequestBody RequestClientDTO clientDto){
        return service.updateClient(id,new Client(clientDto.getName(),clientDto.getDescription()));
    }

    @ApiOperation(value = "Return List of all clients from base")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ResponseClientDTO> getAllClients(){
        List<ResponseClientDTO> result = new ArrayList<>();
        service.getAllClients().forEach(client -> result.add(new ResponseClientDTO(client)));
        return result;
    }

    @ApiOperation(value = "Get client with specified id from base")
    @GetMapping(value = "/{id}")
    public ResponseClientDTO getClient(@PathVariable("id") long id){
        return new ResponseClientDTO(service.getClientById(id));
    }

}
