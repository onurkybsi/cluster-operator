package org.kyb.clusteroperator.services.informative.models;

public class Deployment {
    private String _name;

    public Deployment(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
