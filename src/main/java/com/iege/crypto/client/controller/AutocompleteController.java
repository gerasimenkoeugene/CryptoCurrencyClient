package com.iege.crypto.client.controller;

import com.iege.crypto.client.converter.CryptoTagConverter;
import com.iege.crypto.client.entity.CryptoCurrency;
import com.iege.crypto.client.service.CryptoCurrencyService;
import com.iege.crypto.client.wrapper.CryptoCurrencyTag;
import com.iege.crypto.client.wrapper.SuggestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AutocompleteController {
    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoTagConverter cryptoTagConverter;

    @Autowired
    public AutocompleteController(CryptoCurrencyService cryptoCurrencyService, CryptoTagConverter cryptoTagConverter) {
        this.cryptoCurrencyService = cryptoCurrencyService;
        this.cryptoTagConverter = cryptoTagConverter;
    }

    @ResponseBody
    @RequestMapping(value = "/suggestion", method = RequestMethod.GET, produces = "application/json")
    public SuggestionWrapper autocompleteSuggestions(@RequestParam("searchstr") String searchstr) {
        ArrayList<CryptoCurrencyTag> suggestions = new ArrayList<>();
        List<CryptoCurrency> cryptoCurrencies = cryptoCurrencyService.getCryptoCurrencyList();
        for (CryptoCurrency currency : cryptoCurrencies) {
            if (currency.getName().toLowerCase().contains(searchstr.toLowerCase())) {
                suggestions.add(cryptoTagConverter.convert(currency));
            }
        }
        // truncate the list to the first n, max 20 elements
        int n = suggestions.size() > 20 ? 20 : suggestions.size();
        List<CryptoCurrencyTag> subList = new ArrayList<>(suggestions.subList(0, n));
        SuggestionWrapper sw = new SuggestionWrapper();
        sw.setSuggestions(subList);
        return sw;
    }


}
