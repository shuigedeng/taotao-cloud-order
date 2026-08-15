package com.taotao.cloud.order.infrastructure.configuration.client;

public class ServiceCallException extends RuntimeException {
    public ServiceCallException(String message) {
        super(message);
    }
}
