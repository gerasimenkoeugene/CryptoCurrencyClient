package com.iege.crypto.client.service.impl;

import com.iege.crypto.client.entity.Monitoring;
import com.iege.crypto.client.entity.SecUserDetails;
import com.iege.crypto.client.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${rest.api.url}")
    private String restApiUrl;

    @Override
    public List<Monitoring> getAllUserMonitorings() {
        SecUserDetails secUserDetails = (SecUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Arrays.asList(restTemplate.getForEntity(restApiUrl + "monitorings/user?idUser=" + secUserDetails.getUser().getId(), Monitoring[].class).getBody());
    }

    @Override
    public Monitoring getById(String id) {
        return restTemplate.getForEntity(restApiUrl + "monitorings?monitoringId=" +id, Monitoring.class).getBody();
    }

    @Override
    public void save(Monitoring monitoring) {
        restTemplate.postForEntity(restApiUrl + "monitorings", monitoring, Monitoring.class);
    }

    @Override
    public void delete(String id) {
        restTemplate.delete(restApiUrl + "monitorings?monitoringId=" +id);
    }

    @Override
    public void turnOffMonitoring(String id) {
        restTemplate.delete(restApiUrl + "monitorings/deactivate?monitoringId=" + id);
    }

    @Override
    public void turnOffAllUserMonitorings() {
        SecUserDetails secUserDetails = (SecUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        restTemplate.delete(restApiUrl + "monitorings/user/deactivate?idUser=" + secUserDetails.getUser().getId());
    }
}
