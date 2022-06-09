package com.otx.domain.discovery.handler;

import com.otx.domain.discovery.dto.DiscoveryRequest;
import com.otx.domain.discovery.dto.MessageDiscoveryResult;
import com.otx.domain.discovery.service.DiscoveryDataService;
import com.otx.domain.discovery.service.DiscoveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DefaultDiscoveryHandler implements DiscoveryHandler {

    private final DiscoveryService discoveryService;

    private final DiscoveryDataService discoveryDataService;

    @Autowired
    public DefaultDiscoveryHandler(DiscoveryService discoveryService, DiscoveryDataService discoveryDataService) {
        this.discoveryService = discoveryService;
        this.discoveryDataService = discoveryDataService;
    }

    @Override
    public Mono<ServerResponse> discovery(Mono<DiscoveryRequest> discoveryRequest) {
        Mono<MessageDiscoveryResult> publisher = discoveryRequest
                .map(discoveryService::discoveryService)
                .flatMap(discoveryDataService::add)
                .doOnError(throwable -> log.error("do on error : {}", throwable.getMessage(), throwable));

        return ServerResponse.ok().body(publisher, MessageDiscoveryResult.class);
    }
}
