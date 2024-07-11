package com.godvindockerhub.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResourceLoader {
	
	private static final Logger log =LoggerFactory.getLogger(ResourceLoader.class);
	public static InputStream getResource(String path) throws IOException {
		log.info("reading resource from location {}", path);
		InputStream stream=ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if(Objects.nonNull(stream)) { 
			
			return stream;
		}
		log.info("reading resource from files new stream {}", path);
		return Files.newInputStream(Path.of(path));
		
	}
}
