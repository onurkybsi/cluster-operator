package org.kyb.clusteroperator.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exports a REST service that can be used to get liveliness information of the API
 */
@RestController
public class HealthController {
    @GetMapping("checkHealth")
    public ResponseEntity checkHealth() {
        return ResponseEntity.ok("I'm healthy!");
    }
}