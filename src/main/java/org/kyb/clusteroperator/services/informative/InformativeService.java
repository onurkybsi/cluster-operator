package org.kyb.clusteroperator.services.informative;

import org.kyb.clusteroperator.clusterClients.models.Deployment;

import java.util.List;

/**
 * Contains various services to provide information about the cluster
 */
public interface InformativeService {
    /**
     * Receives metadata of all current deployments in the cluster
     *
     * @return
     */
    List<Deployment> getAllCurrentDeployments();
}
