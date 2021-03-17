package za.co.ashtech.trek.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.ashtech.trek.db.entity.TxLogEntity;

@Repository
public interface TxLogDBRepository extends CrudRepository<TxLogEntity, Long> {
}