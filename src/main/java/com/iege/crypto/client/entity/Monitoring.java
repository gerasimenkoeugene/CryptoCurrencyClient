package com.iege.crypto.client.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
public class Monitoring {
    @Id
    private String id;
    private CryptoCurrency cryptoCurrency;
    private String idUser;
    private String userEmail;
    private String monitoringCondition;
    private Integer conditionValue;
}
