package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ClienteVendaItemInputDTO;
import com.silcom.manager.domain.model.ClienteVendaItemMock;

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
class ClienteVendaItemControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes/{clienteId}/vendas/{vendaId}/itens";

        flyway.migrate();
    }

    @Test
    void shouldReturn200_whenReceiveGET_withClienteValidInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 1)
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
                .pathParam("itemId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{itemId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(1))
                .body("produtoCodigo", containsString("SPE102"))
                .body("quantidade", equalTo(1))
                .body("valorUnitario", equalTo(20.0F))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidClienteInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 99)
                .pathParam("vendaId", 1)
                .pathParam("itemId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
            .get("/{itemId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidVendaInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 99)
                .pathParam("itemId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
            .get("/{itemId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    
    @Test
    void shouldReturn404_whenReceiveGET_withInvalidItemInput() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 1)
                .pathParam("itemId", 99)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
            .get("/{itemId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {
        ClienteVendaItemInputDTO input = ClienteVendaItemMock.getInputInstance();

        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 1)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("produtoCodigo", containsString("SPE102"))
                .body("quantidade", equalTo(2))
                .body("valorUnitario", equalTo(27.20F))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_WhenReceivePOST_withClienteInvalidInput() {
        ClienteVendaItemInputDTO input = ClienteVendaItemMock.getInputInstance();

        RestAssured
            .given()
                .pathParam("clienteId", 99)
                .pathParam("vendaId", 1)
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
                .pathParam("vendaId", 1)
                .pathParam("itemId", 99)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{itemId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn204_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("clienteId", 1)
                .pathParam("vendaId", 1)
                .pathParam("itemId", 2)
                 .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{itemId}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ClienteVendaItemInputDTO input = ClienteVendaItemMock.getInputInstance();

        RestAssured
            .given()
            .pathParam("clienteId", 1)
            .pathParam("vendaId", 3)
            .pathParam("itemId", 3)
            .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{itemId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("produtoCodigo", containsString("SPE102"))
                .body("quantidade", equalTo(2))
                .body("valorUnitario", equalTo(27.20F))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

}
