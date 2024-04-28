package com.example.demo.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ItemController.class)
@ActiveProfiles("test")
class ItemControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private static final String service = "/rest/item/v1";

    @BeforeTestMethod
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    @WithMockUser("admin")
    void checkNames() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/items")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("admin")
    void checkRandom() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/random")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .param("random", "1")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("admin")
    void checkSlower() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/slower")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("admin")
    void checkError() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(service + "/error")
                .secure(false)
                .with(csrf())
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        requestBuilder = MockMvcRequestBuilders.post(service + "/error")
                .secure(false)
                .with(csrf())
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .param("init", "0")
                .characterEncoding("UTF-8");

        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
