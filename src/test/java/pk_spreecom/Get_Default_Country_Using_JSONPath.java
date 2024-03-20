package pk_spreecom;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Get_Default_Country_Using_JSONPath{
  @Test
  public void get_Default_Country_JSONPath() {
	  RestAssured.baseURI = "https://demo.spreecommerce.org";
	  RequestSpecification httpRequest = RestAssured.given();
	  //Response response = httpRequest.get();
	  Response response = httpRequest.request(Method.GET,"/api/v2/storefront/countries/ind");
	  
		// Now let us print the body of the message to see what response
	  // we have received from the server
	  JsonPath js = new JsonPath(response.asString());
		String type_act = js.get("data.type");
		System.out.println(type_act);
		Assert.assertEquals(type_act, "country");
		
		
		String iso_act = js.get("data.attributes.iso_name");
		System.out.println(iso_act);
		Assert.assertEquals(iso_act, "INDIA");
	 
  }
}
