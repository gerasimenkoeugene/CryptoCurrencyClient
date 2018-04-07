package com.iege.crypto.client.service;

import com.iege.crypto.client.entity.CryptoCurrency;

import java.util.List;

public interface CryptoCurrencyService {
    List<CryptoCurrency> getCryptoCurrencyList();
    CryptoCurrency getById(String id);
    CryptoCurrency getByName(String name);
}
