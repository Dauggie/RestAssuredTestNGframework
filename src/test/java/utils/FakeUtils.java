package utils;

import com.github.javafaker.Faker;

public class FakeUtils {

	public static String genericName() {
		Faker faker = new Faker();
		
	 return "Playlist " +faker.regexify("[A-Za-z0-9 ,_-][10]");
	}
	
	public static String genericDescription() {
		Faker faker = new Faker();
		
	 return "Description " +faker.regexify("[A-Za-z0-9 ,_-][50]");
	}
	
}
