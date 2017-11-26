package iaraliev.rashid.bigbroserver.schedule;

import iaraliev.rashid.bigbroserver.model.entity.Camera;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CountCarScheduler {

    private final CameraRepository cameraRepository;
    private final PythonServerRequester requester;
    private Logger logger = LoggerFactory.getLogger(CountCarScheduler.class);

    @Value("${image.folder}")
    private String imageFolder;

    @Autowired
    public CountCarScheduler(CameraRepository cameraRepository, PythonServerRequester requester) {
        this.cameraRepository = cameraRepository;
        this.requester = requester;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 15000)
    private void countCar() {
        for (Camera camera : cameraRepository.findAll()) {
            Long cameraId = camera.getId();
            long penultimateCount = camera.getImgCount() - 1;
            int currentCars = requester.getCarsByPicture(cameraId, penultimateCount).size();
            camera.setCurCars(currentCars);
            cameraRepository.save(camera);
        }
    }
}
