package com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;


@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
public class RequestClientDTO {

    private long id;
    private String name;
    private String description;

    public RequestClientDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public RequestClientDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
