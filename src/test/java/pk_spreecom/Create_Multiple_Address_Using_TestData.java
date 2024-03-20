package pk_spreecom;


import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Create_Multiple_Address_Using_TestData {
	

	//Global Variable
	@Test(dataProvider = "Addresses", dataProviderClass = Spreecom_TestData.class, priority = 1)
	public void addAddress(String fName, String lName, String address1,
			String city, String zipcode, String phone, String state, String country) {
		JSONObject newAddress = new JSONObject();
		newAddress.put("firstname", fName);
		newAddress.put("lastname", lName);
		newAddress.put("address1", address1);
		newAddress.put("city", city);
		newAddress.put("zipcode", zipcode);
		newAddress.put("phone", phone);
		newAddress.put("state_name", state);
		newAddress.put("country_iso", country);
		JSONObject body = new JSONObject();
		body.put("address", newAddress);
		Response response = RestAssured.given()
				.auth()
				.oauth2(Util_Function.oAuth_Token())
				.body(body)
				.contentType(ContentType.JSON)
				.post("https://demo.spreecommerce.org/api/v2/storefront/account/addresses")
				.then()
				.extract()
				.response();
		 int statusCode = response.getStatusCode();
		 Assert.assertEquals(200, statusCode);
		 
		 Map<String, String> default_billing_address = response.jsonPath().getJsonObject("data.attributes");
			String firstName = default_billing_address.get("firstname");
			Assert.assertEquals(firstName, fName);
	}
	
}
