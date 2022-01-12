package org.kyb.clusteroperator.services.informative;

import org.kyb.clusteroperator.services.informative.models.Deployment;

import java.util.List;

/**
 * Contains various services to get information about a cluster
 */
public interface InformativeService {
    /**
     * Receives all current deployments metadata
     *
     * @return
     */
    List<Deployment> getAllCurrentDeployments();
}
