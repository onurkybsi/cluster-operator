package org.kyb.clusteroperator.clusterClients.models;

public class Pod {
    private String _name;

    public Pod(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }
}
