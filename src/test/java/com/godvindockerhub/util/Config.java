package com.godvindockerhub.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Config {
	
	private static final Logger log=LoggerFactory.getLogger(Config.class);
	private static final String DEFAULT_PROPERTIES="config/default.properties";
	private static Properties properties;
	
	public static void initializer() {
		
		properties=loadProperties();
		
		
		for(String key : properties.stringPropertyNames()) {
			if(System.getProperties().containsKey(key)) {
				
				properties.setProperty(key, System.getProperty(key));
			}
		}
		log.info("Test Properties");
		log.info("----------------");
		for(String key : properties.stringPropertyNames()) {
			log.info(" {} , {} ",key,properties.getProperty(key));
		}
		log.info("----------------");
		
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
	
	public static Properties loadProperties() {
		
		Properties properties= new Properties();
		try {
			InputStream stream =ResourceLoader.getResource(DEFAULT_PROPERTIES);
			properties.load(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("unable to load file {} {} new",DEFAULT_PROPERTIES,e);
		}
		return properties;
		
	}
	
	
}
