package com.pet.utils;

import java.util.concurrent.ThreadLocalRandom;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
//import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Vipin Alias Neo De Van
 */
public class ApiUtils {
	// Global Setup Variables
	public static String path;
	public static String jsonPathTerm;
	public static String payload = "";
	public static int MAINID;

	public static void setPayload(int categoryId,
			String categoryName, String petName, String imageURL, int tagID,
			String tagName, String statusVal) {

		payload = "{\n" + " \"id\": " + MAINID + ",\n" + " \"category\": {\n"
				+ " \"id\": " + categoryId + ",\n" + " \"name\": \""
				+ categoryName + "\"\n" + "},\n" + " \"name\": \"" + petName
				+ "\",\n" + " \"photoUrls\": [\n" + " \"" + imageURL + "\"\n"
				+ "],\n" + " \"tags\": [\n" + "{\n" + " \"id\": " + tagID
				+ ",\n" + " \"name\": \"" + tagName + "\"\n" + "}\n" + "],\n"
				+ " \"status\": \"" + statusVal + "\"\n" + "}";
	}
	
	public static int getID(){
		return ThreadLocalRandom.current().nextInt(10000, 99999);
	}

	// Sets Base URI
	public static void setBaseURI() {
		RestAssured.baseURI = PropertyManager.getInstance().getBaseUrl();
	}

	// Sets base path
	public static void setBasePath(String basePathTerm) {
		RestAssured.basePath = basePathTerm;
	}

	// Reset Base URI (after test)
	public static void resetBaseURI() {
		RestAssured.baseURI = null;
	}

	// Reset base path
	public static void resetBasePath() {
		RestAssured.basePath = null;
	}

	// Sets ContentType
	public static void setContentType(ContentType Type) {
		given().contentType(Type);
	}

	public static Response getResponseBody(int id) {
		return given().accept("application/json").contentType(ContentType.JSON)
				.when().get("/pet/" + id);
	}

	public static Response postRequest() {
		return given().accept("application/json").contentType(ContentType.JSON)
				.body(payload).post("/pet");
	}

	public static Response postRequestURLEncoded(int id, String name,
			String status) {
		// return
		// given().accept("application/json").contentType(ContentType.JSON).body(payload).post("/pet");
		return given().urlEncodingEnabled(true).param("name", name)
				.param("status", status)
				.header("Accept", ContentType.JSON.getAcceptHeader())
				.post("/pet/" + id);
	}

	public static Response deleteRequest(int id) {
		// return
		// given().accept("application/json").contentType(ContentType.JSON).body(payload).post("/pet");
		return given().accept("application/json").contentType(ContentType.JSON)
				.header("api_key", "special-key").delete("/pet/" + id);
	}

	// Returns response
	public static Response getResponse() {
		return get();
	}

	public static String getJsonPath(Response res) {
		String json = res.asString();
		return json;
	}

}