package com.otx.domain.discovery.service;

import com.otx.domain.discovery.constant.KafkaConstants;
import com.otx.domain.discovery.dto.MessageDiscoveryResult;
import com.otx.domain.discovery.dto.DiscoveryRequest;
import com.otx.domain.discovery.dto.ServiceInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.TopicListing;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class KafkaDiscoveryService implements DiscoveryService {

    private final int CONNECTION_MAX_IDLE_MS = 10000;
    private final int REQUEST_TIMEOUT_MS = 5000;

    @Override
    public MessageDiscoveryResult discoveryService(DiscoveryRequest discoveryRequest) {
        final ServiceInfo serviceInfo = this.discoveryKafka(discoveryRequest);

        return MessageDiscoveryResult.builder()
                .service(discoveryRequest.getHost(), serviceInfo)
                .build();
    }

    @Override
    public MessageDiscoveryResult discoveryService(List<DiscoveryRequest> discoveryRequestList) {
        final MessageDiscoveryResult.Builder messageDiscoveryResult = MessageDiscoveryResult.builder();

        discoveryRequestList
                .stream()
                .map(this::discoveryKafka)
                .forEach(serviceInfo -> messageDiscoveryResult.service(serviceInfo.getHost(), serviceInfo));

        return messageDiscoveryResult.build();
    }

    private ServiceInfo discoveryKafka(DiscoveryRequest discoveryRequest) {
        try {
            final Collection<TopicListing> topicListings = connect(discoveryRequest);
            log.debug("topics = {}", topicListings);
            return ServiceInfo
                    .builder()
                    .host(discoveryRequest.getHost())
                    .isAlive(true)
                    .topic(topicListings)
                    .build();

        } catch (InterruptedException | ExecutionException e) {
            log.error("[OTX Error] kafka discovery error : {}", e.getMessage(), e);
            return ServiceInfo
                    .builder()
                    .host(discoveryRequest.getHost())
                    .isAlive(false)
                    .error(e.getMessage())
                    .build();
        }
    }

    private Collection<TopicListing> connect(DiscoveryRequest discoveryRequest) throws InterruptedException, ExecutionException {
        try (AdminClient adminClient = KafkaAdminClient.create(getKafkaProperties(discoveryRequest))) {
            return adminClient
                    .listTopics(new ListTopicsOptions().timeoutMs(5000))
                    .listings()
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw e;
        }
    }
    private Map<String, Object> getKafkaProperties(DiscoveryRequest discoveryRequest) {
        return Map.of(
                KafkaConstants.KAFKA_HOST, discoveryRequest.getHost(),
                KafkaConstants.KAFKA_CONNECTION_IDLE_TIME, CONNECTION_MAX_IDLE_MS,
                KafkaConstants.KAFKA_REQUEST_TIME_OUT, REQUEST_TIMEOUT_MS
                );
    }

}
