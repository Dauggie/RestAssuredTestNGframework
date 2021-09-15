package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.Routes.*;
import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {

	public static Response post(String path, String token, Object request ) {
		
		return given(SpecBuilder.getRequestSpec()).
				body(request).
				auth().oauth2(token).
			
		when().
				post(path).
		then().spec(SpecBuilder.getResponseSpec()).
				
				contentType(ContentType.JSON).
				extract().response();
	}
	
	
	public static Response get(String path, String token) {
		
		return
				given(SpecBuilder.getRequestSpec()).
				auth().oauth2(token).
				when().
						get(path).
				then().spec(SpecBuilder.getResponseSpec()).
						
						extract().response();
		
	}
	
	
	
	public static Response update(String path, String token, Object request) {
		
		
		
		return
		given(SpecBuilder.getRequestSpec()).
				body(request).
				auth().oauth2(token).
		when().
				put(path).
		then().spec(SpecBuilder.getResponseSpec()).
				extract().response();
				
		
	}
	
	public static Response postAccount(HashMap<String , String> formparams) {
		
		return	given(SpecBuilder.getAccountRequestSpec())
				
				.formParams(formparams).
				
		when()
				.post(API+""+Token).
		then()	.spec(SpecBuilder.getResponseSpec())
				.extract().response();
		
	}

}
