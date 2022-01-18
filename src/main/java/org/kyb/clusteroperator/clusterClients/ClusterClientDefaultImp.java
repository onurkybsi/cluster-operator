package org.kyb.clusteroperator.clusterClients;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.kyb.clusteroperator.clusterClients.models.ClusterConfig;
import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.clusterClients.models.Pod;
import org.kyb.clusteroperator.common.exceptions.ExternalOperationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClusterClientDefaultImp implements ClusterClient {
    private CoreV1Api api;

    public ClusterClientDefaultImp(ClusterConfig config) {
        try {
            ApiClient client =
                    ClientBuilder
                            .kubeconfig(KubeConfig
                                    .loadKubeConfig(new FileReader(config.getKubeConfigFilePath()))).build();
            Configuration.setDefaultApiClient(client);
        } catch (IOException ex) {
            // TO-DO: Log it!
            throw new ExternalOperationException("Error occurred when ApiClient building!");
        }
        api = new CoreV1Api();
    }

    @Override
    public List<Pod> getAllPods() {
        try {
            V1PodList podList = api.listPodForAllNamespaces(null, null, null, null,
                    null, null, null, null, null, null);
            if (podList == null) {
                // TO-DO: Log!
                throw new ExternalOperationException("V1PodList was received as null!");
            }
            return convertV1PodListToListOfPod(podList);
        } catch (ApiException ex) {
            // TO-DO: Log!
            throw new ExternalOperationException("Error occurred when pod list receiving !" + ex);
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
