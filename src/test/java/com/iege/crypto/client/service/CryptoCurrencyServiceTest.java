package com.iege.crypto.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iege.crypto.client.SpringApp;
import com.iege.crypto.client.entity.CryptoCurrency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringApp.class)
public class CryptoCurrencyServiceTest {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CryptoCurrencyService service;
    @Autowired
    private ObjectMapper objectMapper;
    private MockRestServiceServer mockServer;
    private List<CryptoCurrency> cryptoCurrencies;
    @Value("${rest.api.url}")
    private String restApiUrl;

    @Before
    public void setUp() {
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
        cryptoCurrencies = new ArrayList<>();
        CryptoCurrency btc = new CryptoCurrency("bitcoin", "Bitcoin", "BTC", "1", "9920.26", "1", 1525518572L, 0.6, 0.6, 0.6);
        cryptoCurrencies.add(btc);
    }

    @Test
    public void testGetById() throws JsonProcessingException {
        String cryptoCurrenciesJson = objectMapper.writeValueAsString(cryptoCurrencies);
        mockServer.expect(requestTo(restApiUrl + "cryptocurrency")).andRespond(withSuccess(cryptoCurrenciesJson, MediaType.APPLICATION_JSON));
        assertThat(service.getById("bitcoin").getSymbol()).isEqualTo("BTC");
    }

    @Test
    public void testGetAllCurrencies() {
        assertThat(service.getCryptoCurrencyList().size() == 2);
    }

    @Test
    public void testGetByName() {
        assertThat(service.getByName("Bitcoin").getSymbol().equals("BTC"));
    }
}
