package org.kyb.clusteroperator.api.controllers;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kyb.clusteroperator.clusterClients.configurations.ClusterClientsConfiguration;
import org.kyb.clusteroperator.services.ServicesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("HealthControllerIT")
public class HealthControllerIT {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void initializedWebApplicationContext_Returns_HealthController_Bean() {
        // Arrange
        ServletContext servletContext = webApplicationContext.getServletContext();
        // Act and Assert
        Assert.assertNotNull(webApplicationContext.getBean("healthController"));
    }

    @Test
    public void checkHealth_Returns_Http_Ok_If_Server_Is_Running() throws Exception {
        // Arrange
        RequestBuilder builder = MockMvcRequestBuilders.get("/checkHealth");
        // Act
        MvcResult response = mockMvc.perform(builder)
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
        // Assert
        Assertions.assertThat(response.getResponse().getStatus() == HttpStatus.OK.value());
    }
}
