package za.co.ashtech.trek.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import za.co.ashtech.trek.db.entity.TrailEntity;

@Repository
public interface TrekDBRepository extends CrudRepository<TrailEntity, Long> {
	public TrailEntity findByName(String name);
}