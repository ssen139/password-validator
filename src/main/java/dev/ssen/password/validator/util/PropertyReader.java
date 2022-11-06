package dev.ssen.password.validator.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertyReader {

	private static final Properties properties = new Properties();
	private static PropertyReader propertyReader;
	
	public static PropertyReader getInstance() {
		if(propertyReader == null) {
			propertyReader = new PropertyReader();
		}
		
		return propertyReader;
	}
	
	private PropertyReader() {
		try (InputStream resourceStream = getClass().getClassLoader()
				.getResourceAsStream("application.properties")) {
		    properties.load(resourceStream);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public Optional<String> getProperty(String key) {
		if(properties.containsKey(key))
			return Optional.ofNullable((String) properties.get(key));
		
		return Optional.empty();
	}
}
