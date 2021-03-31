package za.co.ashtech.trek.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.ashtech.trek.db.entity.UserEntity;

@Repository
public interface UserDBRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String username);
}