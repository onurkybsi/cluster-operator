package org.kyb.clusteroperator.clusterClients.models;

/**
 * Represents a model which contains configuration values of the cluster connection
 */
public class ClusterConfig {
    private String _kubeConfigFilePath;

    public ClusterConfig(String kubeConfigFilePath) {
        _kubeConfigFilePath = kubeConfigFilePath;
    }

    public String getKubeConfigFilePath() {
        return _kubeConfigFilePath;
    }
}