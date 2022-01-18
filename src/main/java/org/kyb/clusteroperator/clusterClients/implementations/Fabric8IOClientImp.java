package org.kyb.clusteroperator.clusterClients.implementations;

import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.clusterClients.models.Pod;
import org.kyb.clusteroperator.common.exceptions.ExternalOperationException;

import java.util.ArrayList;
import java.util.List;

public class Fabric8IOClientImp implements ClusterClient {
    private KubernetesClient _kubernetesClient;

    public Fabric8IOClientImp() {
        _kubernetesClient = new DefaultKubernetesClient();
    }

    @Override
    public List<Pod> getAllPods() {
        throw new UnsupportedOperationException("This method hasn't implemented yet!");
    }

    @Override
    public List<Deployment> getAllDeployments() {
        try {
            DeploymentList list = _kubernetesClient.apps().deployments().list();
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
            result.add(new Deployment(fabric8IODeployment.getMetadata().getName()));

        return result;
    }
}
