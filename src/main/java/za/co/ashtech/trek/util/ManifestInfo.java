package za.co.ashtech.trek.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class ManifestInfo {
	
	@ReadOperation
    public Map<String, String> manifestAttributes() throws IOException {
		
		Map<String, String> manifestAttributes = new HashMap<>();
		
		URLClassLoader urlClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader().getClass().getClassLoader();
		URL url = urlClassLoader.findResource("META-INF/MANIFEST.MF");
		try {
			Manifest manifest = new Manifest(url.openStream());
			Attributes attributes = manifest.getMainAttributes();
			
			if(attributes.containsKey("App-Version")) {
				ArrayList<Entry<Object, Object>> attributeList = new ArrayList<>(attributes.entrySet());
				
				for(Entry<Object, Object> entry: attributeList) {
					manifestAttributes.put(entry.getKey().toString(), entry.getValue().toString());
				}				
			}else {
				manifestAttributes.put("Scope", "Local Machine");
			}
			
		} catch (IOException e) {
			manifestAttributes.put("Error", e.getMessage());
		}	
		
		
        return manifestAttributes;
    }

}
