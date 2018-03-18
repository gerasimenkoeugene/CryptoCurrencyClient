package com.iege.crypto.client.controller;

import com.iege.crypto.client.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/monitoring")
@Controller
public class MonitoringController {
    private MonitoringService monitoringService;

    @Autowired
    public void setCustomerService(MonitoringService customerService) {
        this.monitoringService = customerService;
    }

    @RequestMapping({"/list", "/",""})
    public String listCustomers(Model model){
        model.addAttribute("monitorings", monitoringService.getAllUserMonitorings());
        return "monitoring/list";
    }
//
//    @RequestMapping("/show/{id}")
//    public String showCustomer(@PathVariable Integer id, Model model){
//        model.addAttribute("monitoring", customerService.getById(id));
//        return "monitoring/show";
//    }
//
//    @RequestMapping("/edit/{id}")
//    public String edit(@PathVariable Integer id, Model model){
//        model.addAttribute("monitoring", customerService.getById(id));
//        return "monitoring/monitoringform";
//    }
//
//    @RequestMapping("/new")
//    public String newCustomer(Model model){
//        model.addAttribute("monitoring", new Customer());
//        return "monitoring/monitoringform";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public String saveOrUpdate(Customer customer){
//        Customer newCustomer = customerService.saveOrUpdate(customer);
//        return "redirect:customer/show/" + newCustomer.getId();
//    }
//
//    @RequestMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id){
//        customerService.delete(id);
//        return "redirect:/customer/list";
//    }
}
