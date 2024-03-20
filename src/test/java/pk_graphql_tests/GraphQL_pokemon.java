package pk_graphql_tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;

public class GraphQL_pokemon {
	@Test(priority = 1)
	public void getPokemonTest() {
		RestAssured.baseURI = "https://beta.pokeapi.co";
		String query = "{\"query\":\"{\\n  pokemon_v2_pokemon(limit: 10) {\\n    height\\n    id\\n    name\\n    order\\n    pokemon_species_id\\n   }\\n}\"}";
		given().log().all().contentType("application/json").body(query).when().log().all()
				.post("/graphql/v1beta").then().log().all().assertThat().statusCode(200)
				.and().body("data.pokemon_v2_pokemon[1].height", equalTo(10) )
				.and().body("data.pokemon_v2_pokemon[2].name", equalTo("venusaur") );
		
	}
}
