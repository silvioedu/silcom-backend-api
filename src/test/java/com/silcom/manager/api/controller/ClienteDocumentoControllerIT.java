package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ClienteDocumentoInputDTO;
import com.silcom.manager.domain.model.ClienteDocumentoMock;

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
class ClienteDocumentoControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes/{clienteId}/documentos";

        flyway.migrate();
    }

    @Test
    void shouldReturn200_whenReceiveGET_withClienteValidInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn200_whenReceiveGET_withValidInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("documentoId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{documentoId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("tipoDocumentoNome", containsString("CPF"))
                .body("documento", containsString("123456789-12"))
                .body("observacoes", containsString("CPF cliente 1"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 99)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn404_whenReceiveGET_withClienteInvalidInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 99)
                .pathParam("documentoId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{documentoId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {
        ClienteDocumentoInputDTO input = ClienteDocumentoMock.getInputInstance();

        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("tipoDocumentoNome", containsString("CPF"))
                .body("$", hasKey("id"))
                .body("$", hasKey("documento"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_WhenReceivePOST_withClienteInvalidInput() {
        ClienteDocumentoInputDTO input = ClienteDocumentoMock.getInputInstance();

        RestAssured
            .given()
                .pathParam("clienteId", 99)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn404_WhenReceiveDELETE_withNotFoundId() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("documentoId", 10)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{documentoId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn204_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("documentoId", 3)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{documentoId}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ClienteDocumentoInputDTO input = ClienteDocumentoMock.getInputInstance();

        RestAssured
            .given()
            .pathParam("clienteId", 1)
            .pathParam("documentoId", 2)
            .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{documentoId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("tipoDocumentoNome", containsString("CPF"))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("id"))
                .body("$", hasKey("documento"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

}
