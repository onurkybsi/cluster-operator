package org.kyb.clusteroperator.clusterClients;

import org.kyb.clusteroperator.clusterClients.implementations.ClusterClientDefaultImp;
import org.kyb.clusteroperator.clusterClients.implementations.Fabric8IOClientImp;
import org.kyb.clusteroperator.clusterClients.models.ClusterConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * Configures ClusterClients layer dependencies
 */
@Configuration
public class ClusterClientsConfiguration {
    @Value("${CLUSTER_CLIENT_TYPE:fabric8IO}")
    private String clusterClientType;
    @Value("${KUBECONFIG:~/.kube/config}")
    private String kubeConfigFilePath;

    @Bean
    public ClusterClient clusterClient() {
        if (Objects.equals(clusterClientType, "fabric8IO"))
            return new Fabric8IOClientImp();
        return new ClusterClientDefaultImp(new ClusterConfig(kubeConfigFilePath));
    }
}
