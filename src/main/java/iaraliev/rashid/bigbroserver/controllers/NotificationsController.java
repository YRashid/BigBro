package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.model.domain.Car;
import iaraliev.rashid.bigbroserver.model.entity.Camera;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {

    private final CameraRepository cameraRepository;

    @Autowired
    public NotificationsController(CameraRepository cameraRepository, PythonServerRequester pythonServerRequester) {
        this.cameraRepository = cameraRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String getNotification() {
        String result = "12";
        return result;

    }


}
