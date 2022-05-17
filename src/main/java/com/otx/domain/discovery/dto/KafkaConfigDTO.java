package com.otx.domain.discovery.dto;

import java.util.Map;

public class KafkaConfigDTO {

    private final Map<String, Object> configMap;

    private KafkaConfigDTO() {
        this.configMap = Map.of();
    }
    public KafkaConfigDTO(Map<String, Object> configMap) {
        this.configMap = Map.copyOf(configMap);
    }

    static KafkaConfigDTO of(Map.Entry<String, Object>... entry) {
        if (entry.length == 0) {
            return new KafkaConfigDTO();
        }
        return new KafkaConfigDTO(Map.ofEntries(entry));
    }

    static KafkaConfigDTO of(String k, Object v) {
        return new KafkaConfigDTO(Map.of(k, v));
    }

    public static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8, String k9, Object v9) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9));
    }

    static KafkaConfigDTO of(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4, String k5, Object v5, String k6, Object v6, String k7, Object v7, String k8, Object v8, String k9, Object v9, String k10, Object v10) {
        return new KafkaConfigDTO(Map.of(k1, v1, k2, v2, k3, v3, k4, v4, k5, v5, k6, v6, k7, v7, k8, v8, k9, v9, k10, v10));
    }

    
    
}
