package com.myresearchs.swaggerinspring.swaggerinspringexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//данная аннотация позволяет задать файл контекста приложения
@ContextConfiguration(classes = {SwaggerInSpringExampleApplication.class})
//Открывает доступ к веб ресурсам для тестирования контроллеров, например. какие ресурсы и для чего нужны - хз пока
//@WebAppConfiguration
@AutoConfigureMockMvc

public class SwaggerInSpringExampleApplicationTests {

	@LocalServerPort
	int serverPort;

	@Autowired
	ClientRepository repository;

	public static final RequestClientDTO REQUEST_MODEL = new RequestClientDTO("Test Name","Test description");
	public static final String CHANGED_MARKER = "Changed ";

	@Test
	public void getClientTest() throws Exception {
		long id = (repository.save(new Client(REQUEST_MODEL.getName(),REQUEST_MODEL.getDescription()))).getId();
		given()
					.contentType(ContentType.JSON)
				.when()
					.get("client/"+id)
				.then()
					.statusCode(HttpStatus.SC_OK)
					.body(
						"id", is(not(empty())),
						"name", is(REQUEST_MODEL.getName()),
						"description", is(REQUEST_MODEL.getDescription())
					);
	}
	@Test
	public void postClientTest() throws Exception {
		given()
					.contentType(ContentType.JSON)
					.body(REQUEST_MODEL)
				.when()
					.post("client")
				.then()
					.statusCode(HttpStatus.SC_OK)
					.body(
						"id", is(not(empty())),
						"name", is(REQUEST_MODEL.getName()),
						"description", is(REQUEST_MODEL.getDescription())
					);
	}

	@Test
	public void putClientTest() throws Exception {
	long id = (repository.save(new Client(REQUEST_MODEL.getName(),REQUEST_MODEL.getDescription()))).getId();
		given()
					.contentType(ContentType.JSON)
				.body(new RequestClientDTO(
						CHANGED_MARKER + REQUEST_MODEL.getName(),
						CHANGED_MARKER + REQUEST_MODEL.getDescription()))
				.when()
					.put("client/"+id)
				.then()
					.statusCode(HttpStatus.SC_OK)
					.body(
						"id", is(not(empty())),
						"name", is(CHANGED_MARKER + REQUEST_MODEL.getName()),
						"description", is(CHANGED_MARKER + REQUEST_MODEL.getDescription())
					);
	}

	@Before
	public void initRestAssured() {
		RestAssured.port = serverPort;
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}
}
