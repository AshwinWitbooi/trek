package za.co.ashtech.trek.util;

import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.model.Trail;

public class TrekUtil {
	
	public static Trail trailEntityTyModel(TrailEntity entity) {
		Trail trail = new Trail();
		
		trail.setDescription(entity.getDecription());
		trail.setLength(entity.getLength());
		trail.setLevel(entity.getLevel());
		trail.setLocation(entity.getLocation());
		trail.setName(entity.getName());
		trail.setStatus(entity.getStatus());
		
		return trail;
	}

}
