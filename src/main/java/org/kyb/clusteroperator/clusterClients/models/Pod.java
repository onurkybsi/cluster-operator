package org.kyb.clusteroperator.clusterClients.models;

/**
 * Represents a model for the metadata of pods in the cluster
 */
public class Pod {
    private String _name;

    public Pod(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
