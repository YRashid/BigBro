package iaraliev.rashid.bigbroserver.utils;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDevice;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import iaraliev.rashid.bigbroserver.model.entity.Camera;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * //https://github.com/sarxos/webcam-capture
 */
@Component
public class WebCamHelper {
    private Logger logger = LoggerFactory.getLogger(WebCamHelper.class);

    private final CameraRepository cameraRepository;
    @Value("${image.folder}")
    private String imageFolder;

    @Autowired
    public WebCamHelper(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
        registerWebCams();
    }

    public void savePicture(long cameraId, long imgCount) throws IOException {
        Webcam webcam = Webcam.getWebcamByName(String.valueOf(cameraId));
        webcam.open();


        BufferedImage image = webcam.getImage();
        try {
            ImageIO.write(image, "jpeg", new File(imageFolder + cameraId + "_" + imgCount + ".jpeg"));
        } catch (Exception e) {
            logger.error("Error: {}", e);
        }
        webcam.close();
    }


    private void registerWebCams() {
        Webcam.setDriver(new IpCamDriver());
        Iterable<Camera> cameras = cameraRepository.findAll();
        for (Camera camera : cameras) {
            try {
                IpCamDevice device = IpCamDeviceRegistry.register(String.valueOf(camera.getId()), camera.getUrl(), IpCamMode.PUSH);
                int height = device.getResolution().height;
                int width = device.getResolution().width;
                camera.setHeight(height);
                camera.setWidth(width);
                cameraRepository.save(camera);

            } catch (MalformedURLException e) {
                logger.error("Error register WebCam {}: {}", camera.getId(), e);
            }
        }
    }
}
