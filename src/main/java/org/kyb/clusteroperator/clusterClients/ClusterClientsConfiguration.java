package org.kyb.clusteroperator.clusterClients;

import org.kyb.clusteroperator.clusterClients.models.ClusterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * Configures the dependencies of clusterClients package
 */
@Configuration
public class ClusterClientsConfiguration {
    @Value("${CLUSTER_CLIENT_TYPE:fabric8IO}")
    private String clusterClientType;
    @Value("${KUBECONFIG:resources/cluster-config.yaml}")
    private String kubeConfigFilePath;

    @Bean
    public ClusterClient clusterClient() {
        if (Objects.equals(clusterClientType, "fabric8IO"))
            return new Fabric8IOClient();
        return new ClusterClientDefaultImp(new ClusterConfig(kubeConfigFilePath));
    }
}
