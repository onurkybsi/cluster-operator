package org.kyb.clusteroperator.api.controllers;

import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.server.mock.EnableKubernetesMockClient;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kyb.clusteroperator.clusterClients.configurations.ClusterClientsConfiguration;
import org.kyb.clusteroperator.services.ServicesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.ServletContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {
        InformativeControllerIT.MockFabric8KubernetesClientConfiguration.class,
        ClusterClientsConfiguration.class,
        ServicesConfiguration.class,
        InformativeController.class
})
@ActiveProfiles("InformativeControllerIT")
@EnableWebMvc
@EnableKubernetesMockClient(crud = true)
public class InformativeControllerIT {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    private static KubernetesClient mockKubernetesServerClient;

    @Configuration
    public static class MockFabric8KubernetesClientConfiguration {
        @Bean
        public KubernetesClient fabric8KubernetesClient() {
            return mockKubernetesServerClient;
        }
    }

    @Test
    public void initializedWebApplicationContext_Returns_Fabric8KubernetesClient_Bean() {
        // Arrange
        ServletContext servletContext = webApplicationContext.getServletContext();
        // Act and Assert
        Assert.assertNotNull(webApplicationContext.getBean("fabric8KubernetesClient"));
    }

    @Test
    public void initializedWebApplicationContext_Returns_ClusterClient_Bean() {
        // Arrange
        ServletContext servletContext = webApplicationContext.getServletContext();
        // Act and Assert
        Assert.assertNotNull(webApplicationContext.getBean("clusterClient"));
    }

    @Test
    public void initializedWebApplicationContext_Returns_InformationService_Bean() {
        // Arrange
        ServletContext servletContext = webApplicationContext.getServletContext();
        // Act and Assert
        Assert.assertNotNull(webApplicationContext.getBean("informativeService"));
    }

    @Test
    public void initializedWebApplicationContext_Returns_InformativeController_Bean() {
        // Arrange
        ServletContext servletContext = webApplicationContext.getServletContext();
        // Act and Assert
        Assert.assertNotNull(webApplicationContext.getBean(InformativeController.class));
    }

    @Test
    public void getAllCurrentDeployments_Returns_All_Current_Deployments() throws Exception {
        // Arrange
        createDeployment("deployment1", "ns1");
        createDeployment("deployment2", "ns2");
        RequestBuilder builder = MockMvcRequestBuilders
                .get("/informative/getAllCurrentDeployments")
                .accept("application/json");
        // Act and Assert
        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{'name': 'deployment1', 'namespaceName': 'ns1'}, {'name': 'deployment2', 'namespaceName': 'ns2'}]"));
    }

    private void createDeployment(String name, String namespaceName) {
        Deployment deployment = new DeploymentBuilder().withNewMetadata()
                .withName(name)
                .withNamespace(namespaceName)
                .addToLabels("testKey", "testValue")
                .endMetadata()
                .withNewSpec()
                .endSpec()
                .build();
        mockKubernetesServerClient.apps().deployments().inNamespace(namespaceName).create(deployment);
    }
}
