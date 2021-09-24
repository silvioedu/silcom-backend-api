package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ProdutoFabricanteInputDTO;
import com.silcom.manager.domain.model.ProdutoFabricanteMock;

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
class ProdutoFabricanteControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cadastros/produtos/fabricantes";

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
                .body("nome", containsString("Susa"))
                .body("sigla", containsString("02"))
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
                .pathParam("nome", "Susa")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/por-nome?nome={nome}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidInputPorNome() {
        RestAssured
            .given()
                .pathParam("nome", "ZZZ")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/por-nome?nome={nome}")
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
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();

        RestAssured
            .given()
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("nome", containsString(input.getNome()))
                .body("sigla", containsString(input.getSigla().toUpperCase()))
                .body("$", hasKey("id"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn500_WhenReceivePOST_withInvalidInput() {
        ProdutoFabricanteInputDTO input = new ProdutoFabricanteInputDTO();

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
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setNome("Italbotas");

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
    void shouldReturn409_WhenReceivePOST_withDuplicateSiglaInput() {
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setSigla("04");;

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
    void shouldReturn204_WhenReceiveDELETE_withFoundId() {
        RestAssured
            .given()
                .pathParam("id", 19)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn409_WhenReceiveDELETE_withInUseId() {
        RestAssured
            .given()
                .pathParam("id", 1)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setNome("NOVO");

        RestAssured
            .given()
                .pathParam("id", 10)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn409_whenReceivePUT_withDuplicateNomeInput() {
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setNome("Canadá");

        RestAssured
            .given()
                .pathParam("id", 10)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @Test
    void shouldReturn409_whenReceivePUT_withDuplicateSiglaInput() {
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setSigla("13");;

        RestAssured
            .given()
                .pathParam("id", 10)
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
        ProdutoFabricanteInputDTO input = ProdutoFabricanteMock.getInputInstance();
        input.setNome("AUTO POSTO");

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
