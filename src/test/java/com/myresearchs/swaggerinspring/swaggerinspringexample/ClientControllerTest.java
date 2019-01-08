package com.myresearchs.swaggerinspring.swaggerinspringexample;

import com.myresearchs.swaggerinspring.swaggerinspringexample.model.DTO.RequestClientDTO;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import com.google.common.primitives.Ints;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Test;

public class ClientControllerTest extends BaseTest{
    public static final RequestClientDTO REQUEST_MODEL = new RequestClientDTO("Test Name","Test description");
    @Test
    public void createClientMustWork() {

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
}
