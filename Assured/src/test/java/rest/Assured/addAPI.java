package rest.Assured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class addAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
		// ADD API
		String response = given().log().all().queryParam("key ", "qaclick123").header("Content-Type","application/json")
        .body(payloadadd.getPayload()).when().post("/maps/api/place/add/json")
        .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().asString();
		
		System.out.println(response);
		
		//Using Jsonpath class to parse json
		
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		System.out.println(placeId);
		
		//UPDATE API
		
		String newAddress = "Summar walk USA";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"").when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		//GET API 
		
		String getResponse = given().log().all().queryParam("place_id", placeId).queryParam("key" , "qaclick123")
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo("Summar walk USA")).extract().asString();
		
		//jsonpath to get updated address
		JsonPath jp = reusableMethods.getResponse(getResponse);
		String actAddress = jp.getString("address");
		System.out.println(actAddress);
		
		Assert.assertEquals(actAddress,newAddress );
	}

}