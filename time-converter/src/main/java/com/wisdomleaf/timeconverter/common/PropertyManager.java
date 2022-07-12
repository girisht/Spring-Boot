package com.wisdomleaf.timeconverter.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertyManager {

	private Map<String, String> propertyMap = new HashMap<>();

	public Map<String, String> getProperties() throws IOException {
		Map<String, String> map = new HashMap<>();
		Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		for (String key : properties.stringPropertyNames()) {
			String value = properties.getProperty(key);
			map.put(key, value);
		}
		return map;
	}

	public String getPropertyByKey(String propertyKey) {
		try {
			if (propertyMap.isEmpty()) {
				propertyMap = getProperties();
			}
			return propertyMap.get(propertyKey);
		} catch (Exception e) {
			System.out.println("Property not found : PropertyManager - getPropertyByKey() {} " + e);
		}
		return null;
	}

}
