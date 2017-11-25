package iaraliev.rashid.bigbroserver.schedule;

import iaraliev.rashid.bigbroserver.model.entity.Camera;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.utils.WebCamHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class PicturesScheduler {

    private final CameraRepository cameraRepository;
    private final WebCamHelper webCamHelper;
    private Logger logger = LoggerFactory.getLogger(PicturesScheduler.class);

    @Value("${image.folder}")
    private String imageFolder;
    private static final long MAX_PICTURE_COUNT = 15;

    @Autowired
    public PicturesScheduler(CameraRepository cameraRepository, WebCamHelper webCamHelper) {
        this.cameraRepository = cameraRepository;
        this.webCamHelper = webCamHelper;
    }

    /**
     * По идее надо сохранять только те камеры, у которых есть активный мониторинг
     */
//    @Scheduled(initialDelay = 2000, fixedDelay = 3000)
    @Scheduled(initialDelay = 2000, fixedDelay = 8000)
    public void savePicturesByAllCameras() {
        for (Camera camera : cameraRepository.findAll()) {
            Long imgCount = camera.getImgCount();
            Long cameraId = camera.getId();
            try {
                webCamHelper.savePicture(cameraId, imgCount);
            } catch (IOException e) {
                logger.error("Error: ", e);
                return;
            }
            // не thread safe. Можно синхронизироваться по камере
            camera.setImgCount(imgCount + 1);
            cameraRepository.save(camera);
        }
    }


    @Scheduled(initialDelay = 9000, fixedDelay = 15000)
    private void deleteOldPictures() {
        for (Camera camera : cameraRepository.findAll()) {
            long imgNum = camera.getImgCount() - MAX_PICTURE_COUNT;
            Long cameraId = camera.getId();

            String path = imageFolder + cameraId + "_" + imgNum + ".jpeg";
            try {
                while (Files.deleteIfExists(Paths.get(path))) {
                    imgNum--;
                    path = imageFolder + cameraId + "_" + imgNum + ".jpeg";

                }
            } catch (IOException e) {
                logger.error("Error: ", e);
            }
        }
    }
}
