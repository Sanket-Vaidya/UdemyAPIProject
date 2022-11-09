package project;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetUserRestAssured {

	@Test
	public void getRequest() {
		RestAssured.given().contentType("application/json")
				.header("Authorization", "Bearer 2a34c2d6931041949491bc131720682ee0e60a249ecd39dbdac9598cdc1a1e04")
				.when().get("https://gorest.co.in/public/v2/users").then().statusCode(200).log().all();

	}

}
