package com.example.demo.rest;

import com.example.demo.jpa.ItemRepository;
import com.example.demo.service.DemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ItemController.class)
@ActiveProfiles("test")
public class ItemControllerTest {

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private DemoService demoService;

    @Autowired
    private MockMvc mockMvc;

    private static final String service = "/rest/item/v1";

    @Test
    public void checkNames() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/items")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void checkRandom() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/random")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .param("random", "1")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void checkSlower() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + "/slower")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void checkError() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(service + "/error")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

        requestBuilder = MockMvcRequestBuilders.post(service + "/error")
                .secure(false)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=")
                .param("init", "0")
                .characterEncoding("UTF-8");

        result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
