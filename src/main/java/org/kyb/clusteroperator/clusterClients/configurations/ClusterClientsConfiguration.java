package org.kyb.clusteroperator.clusterClients.configurations;

import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.implementations.ClusterClientDefaultImp;
import org.kyb.clusteroperator.clusterClients.implementations.Fabric8ClientImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
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
    public ClusterClient clusterClient(@Autowired KubernetesClient fabric8KubernetesClient) throws IOException {
        if (Objects.equals(clusterClientType, "fabric8IO")) {
            return new Fabric8ClientImp(fabric8KubernetesClient);
        }
        return createDefaultClient();
    }

    protected ClusterClient createDefaultClient() throws IOException {
        return new ClusterClientDefaultImp(createCoreV1Api());
    }

    /**
     * Creates official Kubernetes client
     *
     * @return
     * @throws IOException
     */
    private CoreV1Api createCoreV1Api()
            throws IOException {
        ApiClient client =
                ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeconfigFilePath)))
                        .build();
        io.kubernetes.client.openapi.Configuration.setDefaultApiClient(client);
        return new CoreV1Api();
    }
}
