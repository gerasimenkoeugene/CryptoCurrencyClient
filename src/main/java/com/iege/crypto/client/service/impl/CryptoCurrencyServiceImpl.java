package com.iege.crypto.client.service.impl;

import com.iege.crypto.client.entity.CryptoCurrency;
import com.iege.crypto.client.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CryptoCurrencyServiceImpl implements CryptoCurrencyService {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${rest.api.url}")
    private String restApiUrl;

    private static List<CryptoCurrency> cryptoCurrencies;

    @Override
    public List<CryptoCurrency> getCryptoCurrencyList() {
        if (cryptoCurrencies == null) cryptoCurrencies = Arrays.asList(restTemplate.getForEntity(restApiUrl + "/cryptocurrency", CryptoCurrency[].class).getBody());
        return cryptoCurrencies;
    }

    @Override
    public CryptoCurrency getById(String id) {
        if (cryptoCurrencies == null) cryptoCurrencies = Arrays.asList(restTemplate.getForEntity(restApiUrl + "/cryptocurrency", CryptoCurrency[].class).getBody());
        return cryptoCurrencies.stream().filter(cryptoCurrency -> cryptoCurrency.getId().equals(id)).findFirst().get();
    }

    @Override
    public CryptoCurrency getByName(String name) {
        if (cryptoCurrencies == null) cryptoCurrencies = Arrays.asList(restTemplate.getForEntity(restApiUrl + "/cryptocurrency", CryptoCurrency[].class).getBody());
        return cryptoCurrencies.stream().filter(cryptoCurrency -> cryptoCurrency.getName().equals(name)).findFirst().get();
    }
}
