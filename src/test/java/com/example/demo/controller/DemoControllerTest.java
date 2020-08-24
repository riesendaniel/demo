package com.example.demo.controller;

import com.example.demo.service.DemoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DemoController.class)
public class DemoControllerTest {

    @MockBean
    private DemoRepository demoRepository;

    @Autowired
    private MockMvc mockMvc;

    private static String service = "/rest/demo/v1";

    @Test
    public void checkNames() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/names")
                .secure(false)
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void checkRandom() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/random")
                .secure(false)
                .param("random", "1")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void checkSlower() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/slower")
                .secure(false)
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
