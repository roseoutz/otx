package com.otx.domain.discovery.handler;

import com.otx.domain.discovery.dto.DiscoveryRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DiscoveryHandler {

    Mono<ServerResponse> discovery(Mono<DiscoveryRequest> discoveryRequest);
}
