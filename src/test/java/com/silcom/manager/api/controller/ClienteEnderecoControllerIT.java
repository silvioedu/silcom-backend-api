package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ClienteEnderecoInputDTO;
import com.silcom.manager.domain.model.ClienteEnderecoMock;

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
class ClienteEnderecoControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/clientes/{clienteId}/enderecos";

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
                .pathParam("clienteId", 3)
                .pathParam("enderecoId", 3)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{enderecoId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(3))
                .body("cep", containsString("53550-610"))
                .body("logradouro", containsString("Rua Cinquenta e Oito"))
                .body("numero", containsString("942"))
                .body("complemento", containsString(""))
                .body("bairro", containsString("Planalto"))
                .body("cidade", containsString("Abreu e Lima"))
                .body("estado", containsString("PE"))
                .body("observacoes", containsString("Endereço 1 cliente 3"))
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
                .pathParam("enderecoId", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{enderecoId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn201_WhenReceivePOST_withValidInput() {
        ClienteEnderecoInputDTO input = ClienteEnderecoMock.getInputInstance();

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
                .body("cep", containsString(input.getCep()))
                .body("logradouro", containsString(input.getLogradouro()))
                .body("numero", containsString(input.getNumero()))
                .body("complemento", containsString(input.getComplemento()))
                .body("bairro", containsString(input.getBairro()))
                .body("cidade", containsString(input.getCidade()))
                .body("estado", containsString(input.getEstado()))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_WhenReceivePOST_withClienteInvalidInput() {
        ClienteEnderecoInputDTO input = ClienteEnderecoMock.getInputInstance();

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
                .pathParam("enderecoId", 10)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{enderecoId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void shouldReturn204_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("clienteId", 3)
                .pathParam("enderecoId", 1)
            .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{enderecoId}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ClienteEnderecoInputDTO input = ClienteEnderecoMock.getInputInstance();

        RestAssured
            .given()
            .pathParam("clienteId", 4)
            .pathParam("enderecoId", 4)
            .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{enderecoId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("cep", containsString(input.getCep()))
                .body("logradouro", containsString(input.getLogradouro()))
                .body("numero", containsString(input.getNumero()))
                .body("complemento", containsString(input.getComplemento()))
                .body("bairro", containsString(input.getBairro()))
                .body("cidade", containsString(input.getCidade()))
                .body("estado", containsString(input.getEstado()))
                .body("observacoes", containsString(input.getObservacoes()))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

}
