package com.godvindockerhub.util;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.godvindockerhub.tests.vendorportal.modal.VendorPortalTestData;

public class JsonUtil {
	
	private static final Logger log =LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper mapper=new ObjectMapper();
	
	public static <T> T getTestData(String path,Class<T> type) throws IOException  {
		try (
			InputStream stream=ResourceLoader.getResource(path);
			
		){
			
			return mapper.readValue(stream, type);
			
			
		}
		catch(Exception e){
			log.error("unable to read file {} {}",path,e);
		}
		
		return null;
		
	}

}
