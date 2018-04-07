package com.iege.crypto.client.converter;

import com.iege.crypto.client.entity.CryptoCurrency;
import com.iege.crypto.client.wrapper.CryptoCurrencyTag;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CryptoTagConverter implements Converter<CryptoCurrency, CryptoCurrencyTag>{
    @Override
    public CryptoCurrencyTag convert(CryptoCurrency cryptoCurrency) {
        return
                new CryptoCurrencyTag(
                        cryptoCurrency.getName(),
                        cryptoCurrency.getId()
                );
    }
}
