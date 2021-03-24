package za.co.ashtech.trek.util;

import za.co.ashtech.trek.db.entity.TrailEntity;
import za.co.ashtech.trek.model.Trail;

public class TrekUtil {
	
	private TrekUtil() {
		
	}
	
	public static Trail trailEntityToModel(TrailEntity entity) {
		Trail trail = new Trail();
		
		trail.setId(Integer.toString(entity.getId()));
		trail.setDescription(entity.getDecription());
		trail.setLength(entity.getLength());
		trail.setLevel(entity.getLevel());
		trail.setLocation(entity.getLocation());
		trail.setName(entity.getName());
		trail.setStatus(entity.getStatus());
		
		return trail;
	}
	
	public static TrailEntity trailModelToEntity(Trail model) {
		TrailEntity trailEntity = new TrailEntity();
		
		trailEntity.setDecription(model.getDescription());
		trailEntity.setLength(model.getLength());
		trailEntity.setLevel(model.getLevel());
		trailEntity.setLocation(model.getLocation());
		trailEntity.setName(model.getName());
		trailEntity.setStatus(model.getStatus());
		
		return trailEntity;
	}

}
