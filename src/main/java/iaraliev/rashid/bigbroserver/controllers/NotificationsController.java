package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notifications")
public class NotificationsController {

    private volatile boolean doIt = false;

    @Autowired
    public NotificationsController(CameraRepository cameraRepository, PythonServerRequester pythonServerRequester) {
        this.cameraRepository = cameraRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    String getNotification() {
        String result = null;
        if (doIt) {
            result = "Take your car away because there will be snow cleaning";
            doIt = false;
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean addNotifiaction() {
        doIt = true;
        return doIt;
    }


}
