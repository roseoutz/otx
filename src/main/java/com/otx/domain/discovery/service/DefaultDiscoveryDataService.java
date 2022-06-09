package com.otx.domain.discovery.service;

import com.otx.domain.discovery.document.ServiceInfoDocument;
import com.otx.domain.discovery.dto.MessageDiscoveryResult;
import com.otx.domain.discovery.dto.ServiceInfo;
import com.otx.domain.discovery.repo.MessagingServiceInfoRepository;
import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class DefaultDiscoveryDataService implements DiscoveryDataService{

    private final MessagingServiceInfoRepository messagingServiceInfoRepository;

    @Autowired
    public DefaultDiscoveryDataService(MessagingServiceInfoRepository messagingServiceInfoRepository) {
        this.messagingServiceInfoRepository = messagingServiceInfoRepository;
    }

    private String generateOid() {
        return Base64.encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public Mono<ServiceInfo> add(ServiceInfo serverInfo) {
        ServiceInfoDocument document = new ServiceInfoDocument(generateOid(), serverInfo.getHost(), "KAFKA", serverInfo.isAlive(), serverInfo.getError(), System.currentTimeMillis());

        Mono<ServiceInfo> serviceInfoMono = messagingServiceInfoRepository.save(document)
                .thenReturn(serverInfo);

        return messagingServiceInfoRepository.findByHost(document.getHost())
                .filter(Objects::isNull)
                .then(serviceInfoMono);
    }

    @Override
    public Mono<MessageDiscoveryResult> add(MessageDiscoveryResult messageDiscoveryResult) {
        Stream<ServiceInfo> serverInfoStream = messageDiscoveryResult.getServices()
                .values()
                .stream();

        return Flux.fromStream(serverInfoStream)
                .flatMap(this::add)
                .then(Mono.just(messageDiscoveryResult));
    }
}
