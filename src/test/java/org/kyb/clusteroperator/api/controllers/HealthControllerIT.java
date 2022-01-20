package org.kyb.clusteroperator.api.controllers;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HealthController.class)
public class HealthControllerIT {
    @Autowired
    static MockMvc mockMvc;

    @Test
    public void checkHealth_Returns_Http_Ok_If_Server_Is_Running() throws Exception {
        // Arrange
        RequestBuilder builder = MockMvcRequestBuilders.get("/checkHealth");
        // Act
        MvcResult response = mockMvc.perform(builder).andReturn();
        // Assert
        Assertions.assertThat(response.getResponse().getStatus() == HttpStatus.OK.value());
    }
}
