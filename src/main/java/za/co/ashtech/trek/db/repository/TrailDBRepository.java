package za.co.ashtech.trek.db.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.ashtech.trek.db.entity.TrailEntity;

@Repository
public interface TrailDBRepository extends CrudRepository<TrailEntity, Long> {
	public TrailEntity findByName(String name);
	public List<TrailEntity> findByLocation(String location);
}