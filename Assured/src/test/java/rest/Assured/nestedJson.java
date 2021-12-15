package rest.Assured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class nestedJson {
	
	@Test
	public void exercises() {
		// TODO Auto-generated method stub
		
	JsonPath json = new JsonPath(payloadadd.getNestedResponse());
	
	//System.out.println(json.toString());
	
	//1. Print No of courses returned by API
	
	int courseNOs = json.getInt("courses.size()");
	System.out.println(courseNOs);
	
	//2.Print Purchase Amount
	
    System.out.println(json.get("dashboard.purchaseAmount").toString());
    
    //3. Print Title of the first course
    
    String courseNameone = json.getString("courses[0].title");
    System.out.println(courseNameone);
    
    //4. Print All course titles and their respective Prices
    String arr = null;
    
    for(int i=0; i<courseNOs ;i++ )
    {
    	 arr = json.getString("courses["+i+"].title");
    	System.out.println(arr);
    	System.out.println(json.getString("courses["+i+"].price"));
    }
    
    //5. Print no of copies sold by RPA Course
    for(int i=0; i<courseNOs ;i++ ) 	
    	
    {
    	arr = json.getString("courses["+i+"].title");
    	if(arr.equalsIgnoreCase("RPA"))
    	{
    		System.out.println(json.getString("courses["+i+"].copies"));
    		break;
    	}
    	
    	
    }
    
    
    //6. Verify if Sum of all Course prices matches with Purchase Amount
    int multiply = 0;
    for(int i =0 ;i< courseNOs ; i++)
    {
    	int prices = json.get("courses["+i+"].price");
    	int copies = json.get("courses["+i+"].copies");
    	 multiply +=  prices*copies ;
    	 
    	 System.out.println(multiply);   
    	
    }
    
    System.out.println(multiply);
    
    int expected = json.getInt("dashboard.purchaseAmount");
    
    Assert.assertEquals(multiply, expected);

	}

}
