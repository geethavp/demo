package rest.Assured;

import io.restassured.path.json.JsonPath;

public class reusableMethods {
	
	
	public static JsonPath getResponse(String response)
	{
		JsonPath jp = new JsonPath(response);
		return jp;
		
	}
	
	
}
