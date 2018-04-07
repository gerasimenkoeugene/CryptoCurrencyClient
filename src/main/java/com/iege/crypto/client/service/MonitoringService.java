package com.iege.crypto.client.service;

import com.iege.crypto.client.entity.Monitoring;

import java.util.List;

public interface MonitoringService {
    List<Monitoring> getAllUserMonitorings();
    Monitoring getById(Integer id);
    void save(Monitoring monitoring);
}
