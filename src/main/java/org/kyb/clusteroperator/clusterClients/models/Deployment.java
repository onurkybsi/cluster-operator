package org.kyb.clusteroperator.clusterClients.models;

/**
 * Represents a model for the metadata of deployments in the cluster
 */
public class Deployment {
    private String _name;

    public Deployment(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
