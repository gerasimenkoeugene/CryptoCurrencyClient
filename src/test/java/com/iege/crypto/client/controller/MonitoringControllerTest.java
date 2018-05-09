package com.iege.crypto.client.controller;

import com.iege.crypto.client.entity.Monitoring;
import com.iege.crypto.client.entity.SecUserDetails;
import com.iege.crypto.client.entity.User;
import com.iege.crypto.client.service.CryptoCurrencyService;
import com.iege.crypto.client.service.MonitoringService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MonitoringControllerTest {
    @Mock
    private MonitoringService monitoringService;
    @Mock
    private CryptoCurrencyService cryptoCurrencyService;
    @InjectMocks
    private MonitoringController monitoringController;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(monitoringController).build();
        Authentication auth = new UsernamePasswordAuthenticationToken(new SecUserDetails(new User("1", "test", "test", "", "", true)), null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Test
    public void testListMonitorings() throws Exception {
        mockMvc.perform(get("/monitoring/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("monitoring/list"))
                .andExpect(model().attributeExists("monitorings"));
    }

    @Test
    public void testNewMonitoring() throws Exception {
        mockMvc.perform(get("/monitoring/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("monitoring/monitoringform"))
                .andExpect(model().attributeExists("monitoring", "monitoringConditions"));
    }

    @Test
    public void testLoadCryptoCurrency() throws Exception {
        RequestBuilder request = get("/monitoring/load")
                .param("cryptoCurrencyName", "cryptoCurrencyName");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("monitoring/monitoringform"))
                .andExpect(model().attributeExists("monitoring", "monitoringConditions"));
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/monitoring/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/monitoring/list"));
    }

    @Test
    public void testEdit() throws Exception {
        when(monitoringService.getById("1")).thenReturn(new Monitoring());
        mockMvc.perform(get("/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("monitoring/monitoringform"))
                .andExpect(model().attributeExists("monitoring", "monitoringConditions"));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(get("/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/monitoring/list"));
    }

    @Test
    public void testTurnOffMonitoring() throws Exception {
        mockMvc.perform(get("/deactivate/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/monitoring/list"));
    }

    @Test
    public void testTurnOffAllUserMonitorings() throws Exception {
        mockMvc.perform(get("/deactivateAll"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/monitoring/list"));
    }
}
