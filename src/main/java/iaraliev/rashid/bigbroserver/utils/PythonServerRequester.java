package iaraliev.rashid.bigbroserver.utils;

import iaraliev.rashid.bigbroserver.model.domain.Car;
import iaraliev.rashid.bigbroserver.model.domain.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Component
public class PythonServerRequester {
    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(PythonServerRequester.class);
    private static final String SOS = "SOS";
    private static final String JPEG = ".jpeg";
    private static final String URL_GET_CARS = "http://localhost:5000/?path=";
    private static final String URL_THEFT = "http://localhost:5001/?path1=%s&path2=%s&top=%s&left=%s&bottom=%s&right=%s";

    @Value("${image.folder}")
    private String imageFolder;

    @Autowired
    public PythonServerRequester(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Car> getCarsByPicture(long cameraId, long imgNumber) {
        String fullUrl = URL_GET_CARS + imageFolder + cameraId + "_" + imgNumber + JPEG;
        List<Car> result = Collections.emptyList();
        try {
            ResponseEntity<List<Car>> response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
            });
            result = response.getBody();
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return result;
    }

    public boolean isHereTheft(long cameraId, long imgNumberFirst, long imgNumberSecond, long top, long left, long bottom, long right) {
        String firstPath = imageFolder + cameraId + "_" + imgNumberFirst + JPEG;
        String secondPath = imageFolder + cameraId + "_" + imgNumberSecond + JPEG;
        String fullUrl = String.format(URL_THEFT, firstPath, secondPath, top, left, bottom, right);

        boolean result = false;
        try {
            ResponseEntity<Status> response = restTemplate.exchange(fullUrl, HttpMethod.GET, null, Status.class);
            result = SOS.equalsIgnoreCase(response.getBody().getStatus());
        } catch (Exception e) {
            logger.error("Error: ", e);
        }
        return result;
    }
}
