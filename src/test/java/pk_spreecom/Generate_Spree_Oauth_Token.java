package pk_spreecom;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Generate_Spree_Oauth_Token{
	  @Test
	  public void oAuth_Token() {
		  
			 RestAssured.baseURI ="https://demo.spreecommerce.org";
			 RequestSpecification request = RestAssured.given();
			 
			 JSONObject requestParams = new JSONObject();
			 requestParams.put("grant_type", "password");
			 requestParams.put("username", "nikitha@spree.com");
			 requestParams.put("password", "spree@123");
			 
			 
		 // Add a header stating the Request body is a JSON
			 request.header("Content-Type", "application/json"); 
			 request.body(requestParams.toJSONString());
			 Response response = request.request(Method.POST,"/spree_oauth/token");
			 response.prettyPrint();
			 int statusCode = response.getStatusCode();
			 //System.out.println(response.asString());
			 Assert.assertEquals(statusCode, 200); 
			 
			 // Now let us print the body of the message to see what response
			  // we have received from the server
			 // String responseBody = response.getBody().asString();
			  
			 // String responseBody = response.getBody().toString();
			  //System.out.println("Response Body is =>  " + responseBody);
			  JsonPath jsonPathEvaluator = response.getBody().jsonPath();
			  String outh_token=jsonPathEvaluator.get("access_token").toString();
			  System.out.println("oAuth Token is =>  " + outh_token);
			  
			  // VErify that Token Type is Bearer or not
			  String ActtokenType=jsonPathEvaluator.get("token_type").toString();
			  String ExpTokenType = "Bearer";
			  Assert.assertEquals(ExpTokenType, ActtokenType);
			  
			  // First get the JsonPath object instance from the Response interface
			  //Assert.assertEquals(responseBody.contains("Product was created with UI.") /*Expected value*/, true /*Actual Value*/, "Response body contains ");
			 }

}
