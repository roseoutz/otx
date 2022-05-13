package com.otx.domain.discovery.dto;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class MessageDiscoveryResult {

    private final Map<String, ServiceInfo> services;

    public MessageDiscoveryResult(Map<String, ServiceInfo> services) {
        this.services = services;
    }

    private MessageDiscoveryResult(Builder builder) {
        this.services = Map.copyOf(builder.services);
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasError() {
        return this.services
                .entrySet()
                .stream()
                .anyMatch(entry -> !entry.getValue().isAlive() || entry.getValue().getError() != null);
    }

    public static class Builder {

        private final Map<String, ServiceInfo> services = new ConcurrentHashMap<>();

        public Builder service(String host, ServiceInfo serviceInfo) {
            this.services.put(host, serviceInfo);
            return this;
        }

        public MessageDiscoveryResult build() {
            return new MessageDiscoveryResult(services);
        }
    }



}
