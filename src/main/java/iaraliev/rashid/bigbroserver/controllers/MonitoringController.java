package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.model.entity.Monitoring;
import iaraliev.rashid.bigbroserver.repository.CameraRepository;
import iaraliev.rashid.bigbroserver.repository.MonitoringRepository;
import iaraliev.rashid.bigbroserver.utils.PythonServerRequester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/monitorings")
public class MonitoringController {

    private final MonitoringRepository monitoringRepository;
    private final CameraRepository cameraRepository;
    private final PythonServerRequester requester;

    @Autowired
    public MonitoringController(MonitoringRepository monitoringRepository, CameraRepository cameraRepository, PythonServerRequester requester) {
        this.monitoringRepository = monitoringRepository;
        this.cameraRepository = cameraRepository;
        this.requester = requester;
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Iterable<Monitoring> getMonitorings() {
        return monitoringRepository.findAll();
    }

    @RequestMapping(value = "{monitoringId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Monitoring getMonitoring(@PathVariable long monitoringId) {

        Monitoring monitoring = monitoringRepository.findOne(monitoringId);
        return monitoring;
    }


    @RequestMapping(value = "users/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Iterable<Monitoring> getMonitoringByUserId(@PathVariable long userId) {

        Iterable<Monitoring> monitorings = monitoringRepository.findByUserId(userId);
        return monitorings;
    }


    @RequestMapping(value = "{monitoringId}/disable", method = RequestMethod.GET)
    public
    @ResponseBody
    Monitoring disableMonitoring(@PathVariable long monitoringId) {
        Monitoring monitoring = monitoringRepository.findOne(monitoringId);
        monitoring.setActive(false);
        monitoring.setHereTheft(false);
        monitoringRepository.save(monitoring);
        return monitoring;
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    String createMonitoring(@RequestBody Monitoring monitoring) {
        Monitoring createdMonitoring = monitoringRepository.save(monitoring);
        Long cameraId = monitoring.getCamera().getId();

        String model = requester.getModel(cameraId, cameraRepository.findOne(cameraId).getImgCount(), monitoring.getTop(), monitoring.getLeft(), monitoring.getBottom(), monitoring.getRight());

        return model;
    }


}
