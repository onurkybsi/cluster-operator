package org.kyb.clusteroperator.services;

import org.kyb.clusteroperator.clusterClients.ClusterClient;
import org.kyb.clusteroperator.services.informative.InformativeService;
import org.kyb.clusteroperator.services.informative.InformativeServiceDefaultImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Services layer dependencies
 */
@Configuration
public class ServicesConfiguration {
    @Bean
    public InformativeService informativeService(@Autowired ClusterClient clusterClient) {
        return new InformativeServiceDefaultImp(clusterClient);
    }
}
