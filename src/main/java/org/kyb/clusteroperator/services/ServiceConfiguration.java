package org.kyb.clusteroperator.services;

import org.kyb.clusteroperator.services.informative.InformativeService;
import org.kyb.clusteroperator.services.informative.InformativeServiceDefaultImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures Service layer dependencies
 */
@Configuration
public class ServiceConfiguration {
    @Bean
    public InformativeService informativeService() {
        // TO-DO: We should pass the cluster metadata
        return new InformativeServiceDefaultImp();
    }
}
