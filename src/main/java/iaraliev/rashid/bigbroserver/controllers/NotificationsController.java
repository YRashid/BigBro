package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.model.domain.Status;
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
    public NotificationsController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Status getNotification() {
        Status status = new Status();
        if (doIt) {
            status.setStatus("Take your car away because there will be snow cleaning");
            doIt = false;
        }
        return status;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean addNotification() {
        doIt = true;
        return doIt;
    }


}
