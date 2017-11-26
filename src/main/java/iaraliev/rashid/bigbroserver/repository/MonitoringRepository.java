package iaraliev.rashid.bigbroserver.repository;

import iaraliev.rashid.bigbroserver.model.entity.Monitoring;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonitoringRepository extends CrudRepository<Monitoring, Long> {
    List<Monitoring> findByIsActive(Boolean isActive);
    List<Monitoring> findByUserId(Long userId);
}
