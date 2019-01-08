package com.myresearchs.swaggerinspring.swaggerinspringexample;


import com.myresearchs.swaggerinspring.swaggerinspringexample.SwaggerInSpringExampleApplication;
import com.myresearchs.swaggerinspring.swaggerinspringexample.containers.PostgresContainerRunner;
import com.myresearchs.swaggerinspring.swaggerinspringexample.containers.TestApplicationInitializer;
import com.myresearchs.swaggerinspring.swaggerinspringexample.repositories.ClientRepository;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("it")
@ContextConfiguration(classes = SwaggerInSpringExampleApplication.class, initializers = TestApplicationInitializer.class)

public class BaseTest {
    @LocalServerPort
    int serverPort;

    @Autowired
    ClientRepository clientRepository;

    static {
        PostgresContainerRunner.run();
    }

    @Before
    public void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void cleanUp() {
        clientRepository.deleteAll();
    }
}
