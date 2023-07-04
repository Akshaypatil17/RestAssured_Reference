package Akshay;

import static io.restassured.RestAssured.given;

import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Rest_Patch_Reference {

	public static void main(String[] args) {

		// Declare Base URL
		RestAssured.baseURI = "https://reqres.in/";

		// Declare the request body string variable
		String requestBody = "{\r\n"+"\"name\":\"morpheus\",\r\n"+ "\"job\":\"zion resident\"\r\n"+"}";

		// Declare the expected results
		JsonPath JspRequest = new JsonPath(requestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentDate = LocalDateTime.now();
		String expecteddate = currentDate.toString().substring(0, 11);

		// Declare the given,when and then method
		String responseBody = given().header("Content-Type", "application/json").body(requestBody).when()
				.patch("api/users/2").then().extract().response().asString();

		System.out.println(responseBody);

		// Create an Object of JSON path to parse the response body
		JsonPath JspResponse = new JsonPath(responseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_updatedAt = JspResponse.getString("updatedAt");
		Res_updatedAt = Res_updatedAt.substring(0, 11);

		// Validate the ResponseBody parameters
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_updatedAt, expecteddate);
	}

}
