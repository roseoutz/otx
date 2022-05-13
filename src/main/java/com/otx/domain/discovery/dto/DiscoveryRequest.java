package com.otx.domain.discovery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class DiscoveryRequest {

    private String host;
    private int timeout;

}
