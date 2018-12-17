package com.myresearchs.swaggerinspring.swaggerinspringexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myresearchs.swaggerinspring.swaggerinspringexample.model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//данная аннотация позволяет задать файл контекста приложения
@ContextConfiguration(classes = {SwaggerInSpringExampleApplication.class})
//Открывает доступ к веб ресурсам для тестирования контроллеров, например. какие ресурсы и для чего нужны - хз пока
@WebAppConfiguration
@AutoConfigureMockMvc

public class SwaggerInSpringExampleApplicationTests {

	@Autowired
	//позволяет прогнать запросы по действующему пути, только без запуска сервера
	private MockMvc mockMvc;
	private static Client MODEL = new Client("Test Name","Test description");
	private ObjectMapper JSON_MAPPER = new ObjectMapper();

	@Test
	public void getClientTest() throws Exception {
		final long[] testResultId = new long[1];
		this.mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON).content(JSON_MAPPER.writeValueAsString(MODEL)))
		.andDo(print())
		.andDo(mvcResult -> testResultId[0] = getClientFromMvcResult(mvcResult).getId());

		this.mockMvc.perform(get("/client/"+testResultId[0]))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(result -> {
					assertThat("Entities fields are different", matchWithModel(result));
						}
				);
	}
	@Test
	public void postClientTest() throws Exception {
		this.mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON).content(JSON_MAPPER.writeValueAsString(MODEL)))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(result -> {
							assertThat("Entities fields are different", matchWithModel(result));
						}
				);
	}
	@Test
	public void putClientTest() throws Exception {
		final long[] testResultId = new long[1];
		this.mockMvc.perform(post("/client").contentType(MediaType.APPLICATION_JSON).content(JSON_MAPPER.writeValueAsString(MODEL)))
				.andDo(print())
				.andDo(mvcResult -> testResultId[0] = getClientFromMvcResult(mvcResult).getId());
		Client changedClient = new Client("Changed","Changed");
		this.mockMvc.perform(put("/client/"+testResultId[0])
				.contentType(MediaType.APPLICATION_JSON).content(JSON_MAPPER.writeValueAsString(changedClient))
				)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(result -> {
							assertThat("Entities fields are the same after changing", !matchWithModel(result));
						}
				);
	}
	public void contextLoads() {
	}

	private Client getClientFromMvcResult(MvcResult result) throws IOException {
		return JSON_MAPPER.readValue(result.getResponse().getContentAsString(),Client.class);
	}
	private boolean matchWithModel(MvcResult result) throws IOException {
		return (getClientFromMvcResult(result).getName().equals(MODEL.getName()))
				&&
				getClientFromMvcResult(result).getDescription().equals(MODEL.getDescription());
	}
}
