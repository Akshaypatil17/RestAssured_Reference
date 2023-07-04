

package Akshay;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class Rest_Get_Reference {
	public static void main(String[] args) {
		//Declare the base URL
		RestAssured.baseURI="https://reqres.in/";
		//Declare the request body string variable
		
		//Declare the given,when and then method
		String responseBody = given().header("Content-Type","application/json").body("requestBody").
				when().get("api/users?page=2").then().extract().asString();
        System.out.println(responseBody);
	}

}
