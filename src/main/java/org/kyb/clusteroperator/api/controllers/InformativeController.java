package org.kyb.clusteroperator.api.controllers;

import org.kyb.clusteroperator.clusterClients.models.Deployment;
import org.kyb.clusteroperator.services.informative.InformativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Exports services of InformativeService as REST services
 */
@RestController
@RequestMapping("/informative")
public class InformativeController {
    @Autowired
    private InformativeService _informativeService;

    /**
     * Exports getAllCurrentDeployments service of InformativeService
     *
     * @return
     */
    @GetMapping("getAllCurrentDeployments")
    public ResponseEntity<?> getAllCurrentDeployments() {
        try {
            List<Deployment> serviceResult = _informativeService.getAllCurrentDeployments();
            return ResponseEntity.ok(serviceResult);
        } catch (Exception ex) {
            // TO-DO: Log the exception!
            return new ResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}