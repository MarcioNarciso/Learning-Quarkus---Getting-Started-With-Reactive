package br.dev.marcionarciso;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;


import java.util.List;

@QuarkusTest
public class FruitResourceTest {

	@Test
	public void testListAllFruits() {
		
		Response response = given()
			.when()
			.get("/fruits")
			.then()
			.statusCode(200)
			.contentType("application/json")
			.extract()
			.response();
		

		assertThat(response.jsonPath().getList("name"))
			.containsExactlyInAnyOrder("Cherry", "Apple", "Banana", "Pear");		
	}
	
	@Test
	public void testCreateFruit() {
		
		given()
			.when()
			.body("{\"name\":\"Pear\"}")
			.contentType("application/json")
			.post("/fruits")
			.then()
			.statusCode(201)
			.body(containsString("\"id\":"), containsString("\"name\":\"Pear\""));
		
	}
	
}
