package com.example.demo.rest;

import com.example.demo.jpa.EmployeeRepository;
import com.example.demo.service.DemoService;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class)
@ActiveProfiles("test")
class EmployeeControllerTest {


    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DemoService demoService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    private static final String service = "/rest/employee/v1";

    @BeforeTestMethod
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @ParameterizedTest
    @WithMockUser("admin")
    @ValueSource(strings = {"/employees", "/employees/count", "/employees/average-salary", "/employees/average-salary?department=IT", "/employees/newest", "/employees/roles"})
    void testAll(String url) throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(service + url)
                .secure(false)
                .header("Authorization", "Basic YWRtaW46YWRtaW4=")
                .characterEncoding("UTF-8");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

}
