package org.kyb.clusteroperator.clusterClients;

import org.kyb.clusteroperator.clusterClients.implementations.ClusterClientDefaultImp;
import org.kyb.clusteroperator.clusterClients.implementations.Fabric8IOClientImp;
import org.kyb.clusteroperator.clusterClients.models.ClusterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Configures ClusterClients layer dependencies
 */
@Configuration
public class ClusterClientsConfiguration {
    @Value("${CLUSTER_CLIENT_TYPE:fabric8IO}")
    private String clusterClientType;
    @Value("${KUBECONFIG_FILE_PATH}")
    private String kubeconfigFilePath;

    @Bean
    public ClusterClient clusterClient() throws IOException {
        if (Objects.equals(clusterClientType, "fabric8IO")) {
            return new Fabric8IOClientImp(new ClusterConfig(kubeconfigFilePath));
        }
        return new ClusterClientDefaultImp(new ClusterConfig(kubeconfigFilePath));
    }
}
