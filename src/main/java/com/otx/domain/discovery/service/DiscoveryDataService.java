package com.otx.domain.discovery.service;

import com.otx.domain.discovery.dto.MessageDiscoveryResult;
import com.otx.domain.discovery.dto.ServiceInfo;
import reactor.core.publisher.Mono;

public interface DiscoveryDataService {

    Mono<ServiceInfo> add(ServiceInfo serverInfo);

    Mono<MessageDiscoveryResult> add(MessageDiscoveryResult messageDiscoveryResult);

}
