package iaraliev.rashid.bigbroserver.controllers;

import iaraliev.rashid.bigbroserver.model.entity.Monitoring;
import iaraliev.rashid.bigbroserver.repository.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/monitorings")
public class MonitoringController {

    private final MonitoringRepository monitoringRepository;

    @Autowired
    public MonitoringController(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
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

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    Monitoring createMonitoring(@RequestBody Monitoring monitoring) {
        Monitoring createdMonitoring = monitoringRepository.save(monitoring);
        return createdMonitoring;
    }


}
