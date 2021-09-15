package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUItils {
	
	public static Properties propertyLoader(String filepath) {
		
		Properties properties = new Properties();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filepath));
				try {
					properties.load(reader);
					reader.close();
				}catch(IOException e) {
					e.printStackTrace();
					throw new RuntimeException("Failed to load file "+filepath);
				}
					
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to find file "+filepath);
		}
		
		return properties;
	}

}
