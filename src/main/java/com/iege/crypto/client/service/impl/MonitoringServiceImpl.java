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
        return Arrays.asList(restTemplate.getForEntity(restApiUrl + "monitorings?idUser=" + secUserDetails.getUser().getId(), Monitoring[].class).getBody());
    }

    @Override
    public Monitoring getById(Integer id) {
        return getAllUserMonitorings().get(0);
    }

    @Override
    public void save(Monitoring monitoring) {
        restTemplate.postForEntity(restApiUrl + "monitorings", monitoring, Monitoring.class);
    }
}
