package com.spotify.oauth2.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.time.Instant;
import java.util.HashMap;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigLoader;

public class TokenManager {
	
	static String access_token;
	static Instant expiry_time;
	
	
	
	public synchronized static String getToken() {
		
		try{
			if(access_token == null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing token...");
				Response response = refreshToken();
				access_token = response.path("access_token");
				int expiryDuration = response.path("expires_in");
				expiry_time = Instant.now().plusSeconds(expiryDuration - 300);
					
			} else {
				System.out.println("Token is still valid");
			}
			
		}catch(Throwable T) {
			throw new RuntimeException("Failed to get Token");
		}
		
		
		
		return access_token;
	}
	
	private static Response refreshToken() {
		
		HashMap<String,String> formparams = new HashMap<String, String>();
		formparams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formparams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formparams.put("client_id", ConfigLoader.getInstance().getClientID());
		formparams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		
		Response response =	RestResource.postAccount(formparams);
				
		if(response.statusCode() != 200) {
			throw new RuntimeException("ALERT! Renew Token failed" );
			
		}
		return response;
	}

}
