package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ClienteVendaInputDTO;
import com.silcom.manager.domain.model.ClienteVendaMock;

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
class ClienteVendaControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes/{clienteId}/vendas";

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
                .pathParam("vendaId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{vendaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("formaPagamentoTipoId", equalTo(1))
                .body("desconto", equalTo(0.0F))
                .body("agravo", equalTo(0.0F))
                .body("valorTotal", equalTo(100.F))
                .body("emitirNota", equalTo(false))
                .body("observacoes", containsString("Observação venda do cliente 1"))
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
                .pathParam("vendaId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{vendaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {
        ClienteVendaInputDTO input = ClienteVendaMock.getInputInstance();

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
                .body("formaPagamentoTipoId", equalTo(1))
                .body("desconto", equalTo(0))
                .body("agravo", equalTo(1))
                .body("valorTotal", equalTo(2))
                .body("emitirNota", equalTo(false))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_WhenReceivePOST_withClienteInvalidInput() {
        ClienteVendaInputDTO input = ClienteVendaMock.getInputInstance();

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
                .pathParam("vendaId", 10)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{vendaId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn204_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 2)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{vendaId}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ClienteVendaInputDTO input = ClienteVendaMock.getInputInstance();

        RestAssured
            .given()
            .pathParam("clienteId", 1)
            .pathParam("vendaId", 3)
            .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{vendaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("formaPagamentoTipoId", equalTo(1))
                .body("desconto", equalTo(0))
                .body("agravo", equalTo(1))
                .body("valorTotal", equalTo(2))
                .body("emitirNota", equalTo(false))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

}
