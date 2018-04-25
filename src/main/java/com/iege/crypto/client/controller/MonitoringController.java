package com.iege.crypto.client.controller;

import com.iege.crypto.client.entity.CryptoCurrency;
import com.iege.crypto.client.entity.Monitoring;
import com.iege.crypto.client.entity.SecUserDetails;
import com.iege.crypto.client.entity.enums.MonitoringCondition;
import com.iege.crypto.client.service.CryptoCurrencyService;
import com.iege.crypto.client.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping({"/monitoring", "/", ""})
@Controller
public class MonitoringController {
    private final MonitoringService monitoringService;
    private final CryptoCurrencyService cryptoCurrencyService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService, CryptoCurrencyService cryptoCurrencyService) {
        this.monitoringService = monitoringService;
        this.cryptoCurrencyService = cryptoCurrencyService;
    }

    @RequestMapping({"/list", "/", ""})
    public String listMonitorings(Model model) {
        model.addAttribute("monitorings", monitoringService.getAllUserMonitorings());
        return "monitoring/list";
    }

    @RequestMapping("/new")
    public String newMonitoring(Model model) {
        Monitoring monitoring = new Monitoring();
        monitoring.setCryptoCurrency(new CryptoCurrency());
        model.addAttribute(monitoring);
        model.addAttribute("monitoringConditions", MonitoringCondition.values());
        return "monitoring/monitoringform";
    }

    @RequestMapping("/load")
    public String loadCryptoCurrency(@RequestParam String cryptoCurrencyName, Model model, Monitoring monitoring) {
        monitoring = StringUtils.isEmpty(monitoring.getId()) ? new Monitoring() : monitoringService.getById(monitoring.getId());
        monitoring.setCryptoCurrency(cryptoCurrencyService.getByName(cryptoCurrencyName));
        monitoring.setActive(true);
        model.addAttribute(monitoring);
        model.addAttribute("monitoringConditions", MonitoringCondition.values());
        return "/monitoring/monitoringform";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String save(Monitoring monitoring) {
        SecUserDetails secUserDetails = (SecUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        monitoring.setIdUser(secUserDetails.getUser().getId());
        monitoring.setUserEmail(secUserDetails.getUser().getEmail());
        if (monitoring.getId().equals("")) monitoring.setId(null);
        monitoringService.save(monitoring);
        return "redirect:/monitoring/list";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        model.addAttribute("monitoring", monitoringService.getById(id));
        model.addAttribute("monitoringConditions", MonitoringCondition.values());
        return "monitoring/monitoringform";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable String id){
        monitoringService.delete(id);
        return "redirect:/monitoring/list";
    }

    @RequestMapping("/deactivate/{id}")
    public String turnOffMonitoring(@PathVariable String id){
        monitoringService.turnOffMonitoring(id);
        return "redirect:/monitoring/list";
    }

    @RequestMapping("/deactivateAll")
    public String turnOffAllUserMonitorings(){
        monitoringService.turnOffAllUserMonitorings();
        return "redirect:/monitoring/list";
    }
}
