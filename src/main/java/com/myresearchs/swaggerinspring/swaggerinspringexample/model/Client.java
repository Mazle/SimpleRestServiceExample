package com.myresearchs.swaggerinspring.swaggerinspringexample.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
@ApiModel
@Entity
@Access(AccessType.FIELD)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, isGetterVisibility = NONE, setterVisibility = NONE)
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(generator = "rest_service_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "rest_service_seq", sequenceName = "rest_service_seq",allocationSize = 1)
    @ApiModelProperty(notes = "id",name="id",required=true,value="test id")
    private long id;

    @ApiModelProperty(notes = "Name of client",name="name",required=true,value="Name of client")
    @Column(name = "name", nullable = false)
    private String name;
    @ApiModelProperty(notes = "Description of client",name="description",required=false,value="Description of client")
    @Column(name = "description", nullable = false)
    private String description;

    public Client(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Client() {
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
