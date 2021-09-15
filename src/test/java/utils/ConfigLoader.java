package utils;

import java.util.Properties;

public class ConfigLoader {
	
	public static  Properties properties;
	public static ConfigLoader configloader;
	
	private ConfigLoader() {
		properties = PropertiesUItils.propertyLoader("src/test/resources/Config.properties");
		
	}

	
	public static ConfigLoader getInstance() {
		if(configloader == null) {
			
			configloader = new ConfigLoader();
		}
	return configloader;
	}
	
	public String getClientID() {
		String prop = properties.getProperty("client_id");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch client ID");
	}
	
	public String getClientSecret() {
		String prop = properties.getProperty("client_secret");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch client_secret");
	}
	
	public String getGrantType() {
		String prop = properties.getProperty("grant_type");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch grant_type");
	}
	
	public String getRefreshToken() {
		String prop = properties.getProperty("refresh_token");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch refresh_token");
	}
	
	public String getUserID() {
		String prop = properties.getProperty("User_id");
		if(prop != null) return prop;
		else throw new RuntimeException("Can't fetch User_id");
	}
	
}
