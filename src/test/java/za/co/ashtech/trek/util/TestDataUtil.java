package za.co.ashtech.trek.util;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataUtil {
	
	
	public static String getTrailname() {
		return RandomStringUtils.randomAlphabetic(8)+" Trail";
	}
	
	public static String getJSONString(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();		
		
		return objectMapper.writeValueAsString(obj);
	}

}
