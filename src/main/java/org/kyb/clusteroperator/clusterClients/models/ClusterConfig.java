package org.kyb.clusteroperator.clusterClients.models;

/**
 * Represents a model which contains configuration values of the cluster connection
 */
public class ClusterConfig {
    private String _kubeconfigFilePath;

    public ClusterConfig(String kubeconfigFilePath) {
        _kubeconfigFilePath = kubeconfigFilePath;
    }

    public String getKubeConfigFilePath() {
        return _kubeconfigFilePath;
    }
}
