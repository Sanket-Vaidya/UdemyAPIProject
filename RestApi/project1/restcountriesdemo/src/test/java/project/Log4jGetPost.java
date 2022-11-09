package project;

import java.util.HashMap;
import java.util.UUID;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import log4jBaseClass.Log4jBaseClass;

public class Log4jGetPost extends Log4jBaseClass {
	
	public static HashMap<String,String> map=new HashMap<String,String>();
	
	UUID uuid=UUID.randomUUID();
	
	String id;
	
	@Test(priority=0)
	public void postData() {
		map.put("name", "Aditya");
		logger.info("Added name");
		map.put("email", "adiyogi"+uuid+"@yahoo.com");
		logger.info("Added email");
		map.put("gender","male");
		logger.info("Added gender");
		map.put("status", "active");
		logger.info("Added status");
		RestAssured.baseURI="https://gorest.co.in";
		RestAssured.basePath="/public/v2/users";
		logger.info("Payload Created");
	}
	
	@Test(priority=1)
	public void createUser() {
	Response response=	RestAssured.given().contentType("application/json").body(map).header("Authorization","Bearer 2a34c2d6931041949491bc131720682ee0e60a249ecd39dbdac9598cdc1a1e04").when().
		post().then().statusCode(201).log().all().contentType(ContentType.JSON).extract().response();
		
		logger.info("Resource created and Response captured");
		
		JsonPath jsonPath=response.jsonPath();
		System.out.println("Response = "+ jsonPath.get("id"));
		
		logger.info("jsonPath created");
		
		id=jsonPath.get("id").toString();
		logger.info("Resource id captured");
	}
	@Test(priority=2)
	public void getUser() {
		RestAssured.given().contentType("application/json")
		.header("Authorization", "Bearer 2a34c2d6931041949491bc131720682ee0e60a249ecd39dbdac9598cdc1a1e04")
		.when().get("https://gorest.co.in/public/v2/users/"+id).then().statusCode(200).log().all();
	}
	

}
