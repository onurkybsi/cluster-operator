package org.kyb.clusteroperator.clusterClients.implementations;

import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.clusterClients.models.Pod;
import org.kyb.clusteroperator.common.exceptions.ExternalOperationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Default implementation of ClusterClient
 */
public class ClusterClientDefaultImp implements ClusterClient {
    private CoreV1Api _coreV1Api;

    public ClusterClientDefaultImp(CoreV1Api coreV1Api) {
        _coreV1Api = coreV1Api;
    }

    @Override
    public List<Pod> getAllPods() {
        try {
            V1PodList podList = _coreV1Api.listPodForAllNamespaces(null, null, null, null,
                    null, null, null, null, null, null);
            if (podList == null)
                throw new ExternalOperationException("V1PodList was received as null!");
            return convertV1PodListToListOfPod(podList);
        } catch (ApiException ex) {
            // TO-DO: Log!
            throw new ExternalOperationException("Error occurred when pod list receiving!");
        }
    }

    private List<Pod> convertV1PodListToListOfPod(V1PodList list) {
        List<Pod> result = new ArrayList<>();

        List<V1Pod> v1PodList = list.getItems();
        for (V1Pod v1Pod : v1PodList) result.add(new Pod(v1Pod.getMetadata().getName()));

        return result;
    }

    @Override
    public List<Deployment> getAllDeployments() {
        throw new UnsupportedOperationException("This method hasn't implemented yet!");
    }
}
