package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ClienteInputDTO;
import com.silcom.manager.domain.model.ClienteMock;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class ClienteControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes";

        flyway.migrate();
    }

    @Test
    void shouldReturn200_whenReceiveGET_withValidInput() {
        RestAssured
            .given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("razaoSocial", containsString("Cliente número 1"))
                .body("nomeFantasia", containsString("Clientão"))
                .body("ramoNome", containsString("AÇOUGUE"))
                .body("tipoPessoa", containsString("J"))
                .body("observacoes", containsString("Observação 1"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }


    @Test
    void shouldReturn404_whenReceiveGET_withInvalidInput() {
        RestAssured
            .given()
                .pathParam("id", 99)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn200_whenReceiveGET_withValidInputPorNome() {
        RestAssured
            .given()
                .pathParam("razaoSocial", "Cliente")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/por-razao-social?razaoSocial={razaoSocial}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidInputPorNome() {
        RestAssured
            .given()
                .pathParam("razaoSocial", "ZZZ")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/por-razao-social?razaoSocial={razaoSocial}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn200AndList_whenReceiveGET() {
        RestAssured
            .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {
        ClienteInputDTO input = ClienteMock.getInputInstance();

        RestAssured
            .given()
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("razaoSocial", containsString(input.getRazaoSocial()))
                .body("nomeFantasia", containsString(input.getNomeFantasia()))
                .body("tipoPessoa", containsString(input.getTipoPessoa()))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("id"))
                .body("$", hasKey("ramoNome"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn400_WhenReceivePOST_withInvalidInput() {
        ClienteInputDTO input = new ClienteInputDTO();
        input.setRazaoSocial("");

        RestAssured
            .given()
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void shouldReturn409_WhenReceivePOST_withDuplicateNomeInput() {
        ClienteInputDTO input = ClienteMock.getInputInstance();
        input.setRazaoSocial("Cliente número 1");

        RestAssured
            .given()
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void shouldReturn404_WhenReceiveDELETE_withNotFoundId() {
        RestAssured
            .given()
                .pathParam("id", 99)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn404_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("id", 3)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ClienteInputDTO input = ClienteMock.getInputInstance();
        input.setRazaoSocial("NOVO");

        RestAssured
            .given()
                .pathParam("id", 4)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn409_whenReceivePUT_withDuplicateRazaoSocialInput() {
        ClienteInputDTO input = ClienteMock.getInputInstance();
        input.setRazaoSocial("Cliente número 1");

        RestAssured
            .given()
                .pathParam("id", 2)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void shouldReturn404_whenReceivePUT_withInvalidInput() {
        ClienteInputDTO input = ClienteMock.getInputInstance();
        input.setRazaoSocial("Cliente número 1");

        RestAssured
            .given()
                .pathParam("id", 99)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());

    }

}
