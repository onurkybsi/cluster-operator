package org.kyb.clusteroperator.services.informative;

import org.kyb.clusteroperator.services.informative.models.Deployment;

import java.util.List;

/**
 * Default implementation of InformationService
 */
public class InformativeServiceDefaultImp implements InformativeService {
    // TO-DO: You should get the cluster information in construction!

    @Override
    public List<Deployment> getAllCurrentDeployments() {
        return null;
    }
}
