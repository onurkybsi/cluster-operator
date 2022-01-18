package org.kyb.clusteroperator.common.exceptions;

/**
 * Represents exceptions occurred when external operation is handling
 */
public class ExternalOperationException extends RuntimeException {
    public ExternalOperationException(String errorMessage) {
        super(errorMessage);
    }

    public ExternalOperationException() {
        super();
    }
}
