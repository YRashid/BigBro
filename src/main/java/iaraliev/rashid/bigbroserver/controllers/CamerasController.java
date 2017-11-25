package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.model.domain.Car;
import iaraliev.rashid.bigbroserver.model.entity.Camera;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import iaraliev.rashid.bigbroserver.utils.WebCamHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cameras")
public class CamerasController {

    private final CameraRepository cameraRepository;
    private final WebCamHelper webCamHelper;
    private final PythonServerRequester pythonServerRequester;

    @Autowired
    public CamerasController(CameraRepository cameraRepository, WebCamHelper webCamHelper, PythonServerRequester pythonServerRequester) {
        this.cameraRepository = cameraRepository;
        this.webCamHelper = webCamHelper;
        this.pythonServerRequester = pythonServerRequester;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Iterable<Camera> getCameras() {
        return cameraRepository.findAll();
    }

    @RequestMapping(value = "{cameraId}/cars", method = RequestMethod.GET)
    public
    @ResponseBody
    Iterable<Car> getCamera(@PathVariable long cameraId) {

        Camera camera = cameraRepository.findOne(cameraId);
        long penultimateCount = camera.getImgCount() - 1;
        //TODO: -1
        return pythonServerRequester.getCarsByPicture(cameraId, penultimateCount);
    }


}
