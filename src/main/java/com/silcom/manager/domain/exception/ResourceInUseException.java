package com.silcom.manager.domain.exception;

public class ResourceInUseException extends RuntimeException {

    public ResourceInUseException(String msg) {
        super(msg);
    }
    
}
