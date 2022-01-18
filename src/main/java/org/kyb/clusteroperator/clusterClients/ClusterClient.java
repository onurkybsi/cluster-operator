package org.kyb.clusteroperator.clusterClients;

import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.clusterClients.models.Pod;

import java.util.List;

/**
 * Represents a client module of the cluster will operate
 */
public interface ClusterClient {
    /**
     * Receives all pods which defined on the cluster
     *
     * @return
     */
    List<Pod> getAllPods();

    /**
     * Receives all deployments which defined on the cluster
     *
     * @return
     */
    List<Deployment> getAllDeployments();
}
