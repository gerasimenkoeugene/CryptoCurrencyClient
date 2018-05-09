package com.iege.crypto.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iege.crypto.client.SpringApp;
import com.iege.crypto.client.entity.CryptoCurrency;
import com.iege.crypto.client.entity.Monitoring;
import com.iege.crypto.client.entity.SecUserDetails;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.entity.enums.MonitoringCondition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringApp.class)
public class MonitoringServiceTest {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private MonitoringService monitoringService;
    @Autowired
    private ObjectMapper objectMapper;
    private MockRestServiceServer mockServer;
    private CryptoCurrency btc;
    private Monitoring monitoring;

    @Value("${rest.api.url}")
    private String restApiUrl;

    @Before
    public void setUp() {
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
        Authentication auth = new UsernamePasswordAuthenticationToken(new SecUserDetails(new User("1", "test", "test", "", "", true)), null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        btc = new CryptoCurrency("bitcoin", "Bitcoin", "BTC", "1", "9920.26", "1", 1525518572L, 0.6, 0.6, 0.6);
        monitoring = new Monitoring("1", btc, "1", "test@mail.ru", MonitoringCondition.MORE_THEN_USD, 100.0, true);
    }

    @Test
    public void testGetById() throws JsonProcessingException {
        String monitoringJson = objectMapper.writeValueAsString(monitoring);
        mockServer.expect(requestTo(restApiUrl + "monitorings?monitoringId=1")).andRespond(withSuccess(monitoringJson, MediaType.APPLICATION_JSON));
        Monitoring monitoring = monitoringService.getById("1");
        assertThat(monitoring.getId()).isEqualTo("1");
        assertThat(monitoring.getUserEmail()).isEqualTo("test@mail.ru");
    }

    @Test
    public void testGetAllUserMonitorings() throws JsonProcessingException {
        Monitoring monitoringLess = new Monitoring("1", btc, "1", "test@mail.ru", MonitoringCondition.LESS_THEN_USD, 100.0, true);
        List<Monitoring> monitoringList = new ArrayList<>();
        monitoringList.add(monitoring);
        monitoringList.add(monitoringLess);
        String monitoringListJson = objectMapper.writeValueAsString(monitoringList);
        mockServer.expect(requestTo(restApiUrl + "monitorings/user?idUser=1")).andRespond(withSuccess(monitoringListJson, MediaType.APPLICATION_JSON));
        assertThat(monitoringService.getAllUserMonitorings().size() == 2);
    }

    @Test
    public void testSave() throws JsonProcessingException {
        mockServer.expect(once(), requestTo(restApiUrl + "monitorings")).andRespond(withSuccess(objectMapper.writeValueAsString(monitoring), MediaType.APPLICATION_JSON));
        monitoringService.save(monitoring);
    }

    @Test
    public void testDelete() throws JsonProcessingException {
        mockServer.expect(once(), requestTo(restApiUrl + "monitorings?monitoringId=1")).andRespond(withSuccess(objectMapper.writeValueAsString(monitoring), MediaType.APPLICATION_JSON));
        monitoringService.delete("1");
    }

    @Test
    public void testTurnOffMonitoring() throws JsonProcessingException {
        mockServer.expect(once(), requestTo(restApiUrl + "monitorings/deactivate?monitoringId=1")).andRespond(withSuccess(objectMapper.writeValueAsString(monitoring), MediaType.APPLICATION_JSON));
        monitoringService.turnOffMonitoring("1");
    }

    @Test
    public void testTurnOffUserMonitoring() throws JsonProcessingException {
        mockServer.expect(once(), requestTo(restApiUrl + "monitorings/user/deactivate?idUser=1")).andRespond(withSuccess(objectMapper.writeValueAsString(monitoring), MediaType.APPLICATION_JSON));
        monitoringService.turnOffAllUserMonitorings();
    }
}
