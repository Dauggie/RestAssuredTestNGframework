package utils;

import java.util.Properties;

public class DataLoader {
	
	public static  Properties properties;
	public static DataLoader dataLoader;
	
	private DataLoader() {
		properties = PropertiesUItils.propertyLoader("src/test/resources/data.properties");
		
	}

	
	public static DataLoader getInstance() {
		if(dataLoader == null) {
			
			dataLoader = new DataLoader();
		}
	return dataLoader;
	}
	
	public String getPlaylistID() {
		String prop = properties.getProperty("get_playlist_id");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch playlist ID");
	}
	
	public String getUpdatePlaylistID() {
		String prop = properties.getProperty("update_playlist_id");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch update playlist ID");
	}
	
	public String getInvalidToken() {
		String prop = properties.getProperty("InvalidToken");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch invalid token");
	}
	
	
	
}
