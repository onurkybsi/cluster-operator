package org.kyb.clusteroperator.common.exceptions;

/**
 * Represents exceptions that occur while handling an external operation
 */
public class ExternalOperationException extends RuntimeException {
    public ExternalOperationException(String errorMessage) {
        super(errorMessage);
    }

    public ExternalOperationException() {
        super();
    }
}
