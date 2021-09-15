package com.spotify.oauth2.tests;

import utils.FakeUtils;
import org.testng.annotations.*;
import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.application.PlaylistAPI;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.spotify.oauth2.api.SpecBuilder;
import com.spotify.oauth2.pojo.*;
import com.spotify.oauth2.pojo.Error;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.http.ContentType;
import io.restassured.internal.http.Status;
import io.restassured.response.Response;
import utils.DataLoader;


@Epic("Spotify OAuth 2.0")
@Feature("playlist API")
public class PlaylistTests {
	@Step
	public Playlist playlistBuilder(String name, String description, boolean _public) {
		return new Playlist().setName(name).setDescription(description).setPublic(_public);
	}

	public void assertPlaylistEqual(Playlist reqPlaylist, Playlist resPlaylist) {
		assertThat(reqPlaylist.getName(), equalTo(resPlaylist.getName()));
		assertThat(reqPlaylist.getDescription(), equalTo(resPlaylist.getDescription()));
		assertThat(reqPlaylist.getPublic(), equalTo(resPlaylist.getPublic()));

	}

	@Story("Create a playlist story")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@TmsLink("12345")
	@Issue("123456")
	@Description("Test for checking whether playlist is being generated")
	@Test (description = "Should be able to create playlist")
	public void createAPlayList() {

		Playlist reqPlaylist = playlistBuilder(FakeUtils.genericName(), FakeUtils.genericDescription(), false);

		Response response = PlaylistAPI.post(reqPlaylist);
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.getCode()));

		Playlist resPlaylist = response.as(Playlist.class);

		assertPlaylistEqual(reqPlaylist, resPlaylist);

	}

	@Test
	public void getAPlayList() {

		Playlist reqPlaylist = playlistBuilder("New Playlist", "New playlist description", false);

		Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistID());
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));

		Playlist resPlaylist = response.as(Playlist.class);

		assertThat(reqPlaylist.getDescription(), equalTo(resPlaylist.getDescription()));
		assertThat(reqPlaylist.getPublic(), equalTo(resPlaylist.getPublic()));

	}

	@Test
	public void updateAPlayList() {

		Playlist reqPlaylist = playlistBuilder("New Playlist Name", "Updated playlist", false);

		Response response = PlaylistAPI.put(reqPlaylist, DataLoader.getInstance().getUpdatePlaylistID());
		assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));

	}

	@Test
	public void doNotCreateAPlayList() {

		Playlist reqPlaylist = playlistBuilder("", FakeUtils.genericDescription(), false);

		Response response = PlaylistAPI.post(reqPlaylist);

		Error error = response.as(Error.class);

		assertThat(error.getError().getStatus(), equalTo(StatusCode.CODE_400.getCode()));
		assertThat(error.getError().getMessage(), equalTo(StatusCode.CODE_400.getMsg()));

	}

	@Test
	public void doNotGetAPlayList() {

		Response response = PlaylistAPI.get(DataLoader.getInstance().getPlaylistID(),
				DataLoader.getInstance().getInvalidToken());

		Error error = response.as(Error.class);

		assertThat(error.getError().getStatus(), equalTo(StatusCode.CODE_401.getCode()));
		assertThat(error.getError().getMessage(), equalTo(StatusCode.CODE_401.getMsg()));

	}

}
