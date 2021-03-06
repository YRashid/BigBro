package iaraliev.rashid.bigbroserver.schedule;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import iaraliev.rashid.bigbroserver.model.entity.Monitoring;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.repository.MonitoringRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class MonitoringScheduler {

    private final MonitoringRepository monitoringRepository;
    private final PythonServerRequester requester;
    private final CameraRepository cameraRepository;
    private Logger logger = LoggerFactory.getLogger(MonitoringScheduler.class);

    @Value("${image.folder}")
    private String imageFolder;
    private static final long MAX_PICTURE_COUNT = 15;

    @Autowired
    public MonitoringScheduler(MonitoringRepository monitoringRepository, PythonServerRequester requester, CameraRepository cameraRepository) {
        this.monitoringRepository = monitoringRepository;
        this.requester = requester;
        this.cameraRepository = cameraRepository;
    }

    @Scheduled(initialDelay = 3000, fixedDelay = 1500)
    public void doMonitoring() throws IOException {

        List<Monitoring> monitorings = monitoringRepository.findByIsActive(true);

        for (Monitoring monitoring : monitorings) {
            Long lastImageNum = monitoring.getCamera().getImgCount() - 1;
            Long cameraId = monitoring.getCamera().getId();

            boolean isHereTheft = requester.isHereTheft(cameraId, lastImageNum, lastImageNum - 1, monitoring.getTop(), monitoring.getLeft(), monitoring.getBottom(), monitoring.getRight());

            if (isHereTheft) {
                monitoring.setHereTheft(true);
                monitoringRepository.save(monitoring);
                generateGif(cameraId, lastImageNum, monitoring.getId());

            }

/*            if (isHereTheft) {
                generateGif(cameraId, lastImageNum + 1, monitoring.getId());
            }*/

            System.out.println("isHereTheft: " + isHereTheft + " cameraId: " + cameraId + " img1: " + lastImageNum + " img2: " + (lastImageNum - 1));
        }
    }

    private void generateGif(long cameraId, long lastImageNumber, long monitoringId) throws FileNotFoundException {

        AnimatedGifEncoder encoder = new AnimatedGifEncoder();

        encoder.start(new FileOutputStream(new File(imageFolder + monitoringId + ".gif")));
        encoder.setRepeat(9);
        encoder.setDelay(400);


        for (int i = 0; i < MAX_PICTURE_COUNT; i++) {
            try {
                String path = imageFolder + cameraId + "_" + (lastImageNumber - i) + ".jpeg";
                encoder.addFrame(getImage(path));
                System.out.println("GIF: " + i);
            } catch (IOException unimportant) {
                break;
            }
        }
        encoder.finish();
    }

    private static BufferedImage getImage(String name) throws IOException {
        return ImageIO.read(new File(name));
    }


}
