package com.silcom.manager.api.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.silcom.manager.api.dto.input.ProdutoInputDTO;
import com.silcom.manager.domain.model.ProdutoMock;

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
class ProdutoControllerIT {
    
    @LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;

    @BeforeAll
	void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cadastros/produtos";

        flyway.migrate();
    }

    @Test
    void shouldReturn200_whenReceiveGET_withValidInput() {
        RestAssured
            .given()
                .pathParam("id", 6)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(6))
                .body("tipoNome", containsString("Sapato"))
                .body("corNome", containsString("Preto"))
                .body("detalheNome", containsString("Elástico"))
                .body("complementoNome", containsString("Monodensidade Bico PVC"))
                .body("fabricanteNome", containsString("Susa"))
                .body("codigo", containsString("SPE102"))
                .body("folder", containsString("     "))
                .body("preco", equalTo(58.0F))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn404_whenReceiveGET_withInvalidInput() {
        RestAssured
            .given()
                .pathParam("id", 999)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .get("/{id}")
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
        ProdutoInputDTO input = ProdutoMock.getInputInstance();

        RestAssured
            .given()
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("tipoNome", containsString("Bota"))
                .body("corNome", containsString("Rosa"))
                .body("complementoNome", containsString("Monodensidade Sem bico"))
                .body("fabricanteNome", containsString("Susa"))
                .body("codigo", containsString("BRA002"))
                .body("$", hasKey("id"))
                .body("$", hasKey("folder"))
                .body("$", hasKey("preco"))
                .body("$", hasKey("dataCriacao"))
                .body("$", hasKey("dataAtualizacao"));
    }

    @Test
    void shouldReturn400_WhenReceivePOST_withInvalidInput() {
        ProdutoInputDTO input = new ProdutoInputDTO();
        input.setCorId(1L);

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
    void shouldReturn404_WhenReceiveDELETE_withNotFoundId() {
        RestAssured
            .given()
                .pathParam("id", 999)
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
                .pathParam("id", 96)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .delete("/{id}")
            .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void shouldReturn200_whenReceivePUT_withValidInput() {
        ProdutoInputDTO input = ProdutoMock.getInputInstance();
        input.setTipoId(2L);
        input.setCorId(3L);
        input.setDetalheId(2L);
        input.setComplementoId(8L);
        input.setFabricanteId(1L);

        RestAssured
            .given()
                .pathParam("id", 7)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void shouldReturn404_whenReceivePUT_withInvalidInput() {
        ProdutoInputDTO input = ProdutoMock.getInputInstance();

        RestAssured
            .given()
                .pathParam("id", 999)
                .body(input)
				.contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .put("/{id}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}
