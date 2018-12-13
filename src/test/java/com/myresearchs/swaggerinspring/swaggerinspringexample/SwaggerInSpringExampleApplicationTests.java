package com.myresearchs.swaggerinspring.swaggerinspringexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
//данная аннотация позволяет задать файл контекста приложения
@ContextConfiguration(classes = {SwaggerInSpringExampleApplication.class})
//Открывает доступ к веб ресурсам для тестирования контроллеров, например. какие ресурсы и для чего нужны - хз пока
@WebAppConfiguration
public class SwaggerInSpringExampleApplicationTests {

	private MockMvc mockMvc;


	public void contextLoads() {
	}

}
