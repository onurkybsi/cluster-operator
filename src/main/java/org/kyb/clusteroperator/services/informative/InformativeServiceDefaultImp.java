package org.kyb.clusteroperator.services.informative;

import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.models.Deployment;

import java.util.List;

/**
 * Default implementation of InformationService
 */
public class InformativeServiceDefaultImp implements InformativeService {
    private ClusterClient _clusterClient;

    public InformativeServiceDefaultImp(ClusterClient clusterClient) {
        _clusterClient = clusterClient;
    }

    @Override
    public List<Deployment> getAllCurrentDeployments() {
        return _clusterClient.getAllDeployments();
    }
}
