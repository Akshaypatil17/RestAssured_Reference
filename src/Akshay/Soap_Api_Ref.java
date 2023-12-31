package Akshay;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.xml.*;

public class Soap_Api_Ref {
	public static void main(String[] args) {
		// Declare The Base URl
		RestAssured.baseURI = "https://www.dataaccess.com/";
		//Declare Request Body
		String RequestBody = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		//Extract Response Body
		String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).
				when().post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(ResponseBody);
		
		//Create a Object of XML Path to Parse the Response Body
		XmlPath XmlResponse = new XmlPath(ResponseBody);
		String ResponseParameter = XmlResponse.getString("NumberToWordsResult");
		System.out.println(ResponseParameter);
		
		// Validate the Response Body
		Assert.assertEquals(ResponseParameter,"one hundred");
	}

}
