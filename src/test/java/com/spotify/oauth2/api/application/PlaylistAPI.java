package com.spotify.oauth2.api.application;
import static com.spotify.oauth2.api.Routes.*;
import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigLoader;

public class PlaylistAPI {
	
	
	@Step
	public static Response post(Playlist requestPlaylist ) {
		
		return RestResource.post(Users+"/"+ConfigLoader.getInstance().getUserID() +Playlist, TokenManager.getToken(), requestPlaylist);
	}
	
	public static Response post(Playlist requestPlaylist , String InvalidToken) {
		
		return RestResource.post(Users+"/"+ConfigLoader.getInstance().getUserID()+Playlist,InvalidToken,requestPlaylist);
		
	}
		
	
	public static Response get(String Playlist_id) {
		
		return RestResource.get(Playlist+"/"+Playlist_id , TokenManager.getToken());
	
		
	}
	
	public static Response get(String Playlist_id , String Token) {
		
		return RestResource.get(Playlist+"/"+Playlist_id,Token);
		
		
	}
	
	public static Response put(Playlist reqPlaylist , String Playlist_id) {
		
		
		return RestResource.update(Playlist+"/"+Playlist_id, TokenManager.getToken(), reqPlaylist);

				
		
	}

}
