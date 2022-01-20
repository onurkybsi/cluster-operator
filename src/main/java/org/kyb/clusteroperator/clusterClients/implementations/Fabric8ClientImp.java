package org.kyb.clusteroperator.clusterClients.implementations;

import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.clusterClients.models.Pod;
import org.kyb.clusteroperator.common.exceptions.ExternalOperationException;

import java.util.ArrayList;
import java.util.List;

/**
 * ClusterClient implementation which uses Fabric8 Kubernetes client
 */
public class Fabric8ClientImp implements ClusterClient {
    private KubernetesClient _kubernetesClient;

    public Fabric8ClientImp(KubernetesClient kubernetesClient) {
        _kubernetesClient = kubernetesClient;
    }

    @Override
    public List<Pod> getAllPods() {
        throw new UnsupportedOperationException("This method hasn't implemented yet!");
    }

    @Override
    public List<Deployment> getAllDeployments() {
        try {
            DeploymentList list = _kubernetesClient.apps().deployments().inAnyNamespace().list();
            if (list == null)
                throw new ExternalOperationException("DeploymentList was received as null!");
            return convertFabric8IODeploymentListToListOfDeployment(list);
        } catch (Exception ex) {
            // TO-DO: Log it!
            throw new ExternalOperationException("Exception occurred when deployment list receiving!");
        }
    }

    private List<Deployment> convertFabric8IODeploymentListToListOfDeployment(DeploymentList list) {
        List<Deployment> result = new ArrayList<>();

        var deploymentList = list.getItems();
        for (io.fabric8.kubernetes.api.model.apps.Deployment fabric8IODeployment : deploymentList)
            result.add(new Deployment(fabric8IODeployment.getMetadata().getName(),
                    fabric8IODeployment.getMetadata().getNamespace()));

        return result;
    }
}
