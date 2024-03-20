package pk_spreecom;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Retrieve_a_Country_IBM{
  @Test
  public void retrieve_a_Country() {
	  RestAssured.baseURI = "https://demo.spreecommerce.org";
	  RequestSpecification httpRequest = RestAssured.given();
	  //Response response = httpRequest.get();
	  Response response = httpRequest.request(Method.GET,"/api/v2/storefront/countries/ind");
	  
		// Now let us print the body of the message to see what response
	  // we have received from the server
	  String responseBody = response.getBody().asString();
	  System.out.println("Response Body is =>  " + responseBody);
	  // Status Code Validation
	  int statusCode = response.getStatusCode();
	  System.out.println("Status code is =>  " + statusCode);
	  Assert.assertEquals(200, statusCode);
 
	  // First get the JsonPath object instance from the Response interface
	  Assert.assertEquals(responseBody.contains("INDIA") /*Expected value*/, true /*Actual Value*/);
	 
  }
}
