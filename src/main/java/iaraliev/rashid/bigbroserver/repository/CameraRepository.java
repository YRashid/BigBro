package iaraliev.rashid.bigbroserver.repository;

import iaraliev.rashid.bigbroserver.model.entity.Camera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CameraRepository extends CrudRepository<Camera, Long> {
}
