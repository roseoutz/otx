package com.otx.router;

import com.otx.domain.discovery.dto.DiscoveryRequest;
import com.otx.domain.discovery.handler.DiscoveryHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class DiscoveryRouter {

    @Bean
    public RouterFunction<ServerResponse> discovery(DiscoveryHandler discoveryHandler) {
        return RouterFunctions
                .route(
                        POST("/api/v1/discovery")
                                .and(accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED)),
                                request -> discoveryHandler.discovery(request.bodyToMono(DiscoveryRequest.class))
                );
    }
}
