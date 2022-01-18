package org.kyb.clusteroperator.clusterClients.models;

/**
 * Represents a model for the metadata of deployments in the cluster
 */
public class Deployment {
    private String _name;
    private String _namespaceName;

    public Deployment(String name, String namespaceName) {
        _name = name;
        _namespaceName = namespaceName;
    }

    public String getName() {
        return _name;
    }

    public String getNamespaceName() {
        return _namespaceName;
    }
}
