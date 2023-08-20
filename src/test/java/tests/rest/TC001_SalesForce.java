package tests.rest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;


public class TC001_SalesForce extends RESTAssuredBase{
	
	@BeforeTest//Reporting
	public void setValues() {
		testCaseName = "Create a new Incident (REST)";
		testDescription = "Create a new Incident and Verify";
		nodes = "Incident";
		authors = "Sarath";
		category = "REST";
		//dataProvider
		dataFileName = "TC001";
		dataFileType = "JSON";
	}

	@Test
	public void createOauth() {		
				
		Response response = login("/services/oauth2/token");
		response.prettyPrint();
		verifyContentType(response, "application/json");
		
		verifyResponseCode(response, 200);
		
		token = getContentWithKey(response, "access_token");
		System.out.println("Token:"+token);
		
		Response response1=LoginWithOauth(token,"services/data/v58.0/sobjects/Lead/");
		response1.prettyPrint();
		id = getContentWithKey(response1, "id");
		System.out.println("id:"+id);
		
		Response response2=getDetails(token,"/services/data/v58.0/sobjects/Lead/"+id);
		response2.prettyPrint();

	}


}




















