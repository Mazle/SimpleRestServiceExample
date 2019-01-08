package com.myresearchs.swaggerinspring.swaggerinspringexample.services;


import java.util.List;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.ResponseClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ResponseClientDTO toResponseClientDTO (Client client);
    Client toClient (RequestClientDTO request);
    List<ResponseClientDTO> toResponseClientDTO(List<Client> clients);
}


