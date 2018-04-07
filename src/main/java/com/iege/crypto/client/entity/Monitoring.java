package com.iege.crypto.client.entity;

import com.iege.crypto.client.entity.enums.MonitoringCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Monitoring {
    @Id
    private String id;
    private CryptoCurrency cryptoCurrency;
    private String idUser;
    private String userEmail;
    private MonitoringCondition monitoringCondition;
    private Double conditionValue;
}
