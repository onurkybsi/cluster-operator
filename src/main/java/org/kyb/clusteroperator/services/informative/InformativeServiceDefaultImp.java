package org.kyb.clusteroperator.services.informative;

import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.models.Deployment;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Default implementation of InformationService
 */
public class InformativeServiceDefaultImp implements InformativeService {
    private String[] kubernetesNamespaces = new String[]{
            "kube-node-lease", "kube-public", "kube-system"
    };

    private ClusterClient _clusterClient;

    public InformativeServiceDefaultImp(ClusterClient clusterClient) {
        _clusterClient = clusterClient;
    }

    @Override
    public List<Deployment> getAllCurrentDeployments() {
        List<Deployment> deployments = _clusterClient.getAllDeployments();
        return filterKubernetesDeployments(deployments);
    }

    private List<Deployment> filterKubernetesDeployments(List<Deployment> deployments) {
        return deployments
                .stream()
                .filter(deployment -> !Arrays.stream(kubernetesNamespaces).anyMatch(deployment.getNamespaceName()::equals))
                .collect(Collectors.toList());
    }
}
