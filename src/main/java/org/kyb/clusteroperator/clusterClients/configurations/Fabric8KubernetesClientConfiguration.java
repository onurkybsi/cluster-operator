package org.kyb.clusteroperator.clusterClients.configurations;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Configures KubernetesClient of Fabric8
 */
@Configuration
public class Fabric8KubernetesClientConfiguration {
    @Value("${KUBECONFIG_FILE_PATH}")
    private String kubeconfigFilePath;

    private Logger logger = LoggerFactory.getLogger(Fabric8KubernetesClientConfiguration.class);

    @Bean
    public KubernetesClient fabric8KubernetesClient()
            throws IOException {
        logger.info("KUBECONFIG_FILE_PATH was received as: " + kubeconfigFilePath);

        Config config;
        if (kubeconfigFilePath != null)
            config = Config.fromKubeconfig(Files.readString(Paths.get(kubeconfigFilePath)));
        else
            config = new ConfigBuilder().build();

        return new DefaultKubernetesClient(config);
    }
}
