package com.iege.crypto.client.controller;

import com.iege.crypto.client.converter.CryptoTagConverter;
import com.iege.crypto.client.service.CryptoCurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AutocompleteControllerTest {
    @Mock
    private CryptoCurrencyService cryptoCurrencyService;
    @Mock
    private CryptoTagConverter cryptoTagConverter;
    @InjectMocks
    private AutocompleteController autocompleteController;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(autocompleteController).build();
    }

    @Test
    public void testAutocompleteSuggestions() throws Exception {
        RequestBuilder request = get("/suggestion")
                .param("searchstr", "searchstr");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.suggestions", hasSize(0)));

    }
}
