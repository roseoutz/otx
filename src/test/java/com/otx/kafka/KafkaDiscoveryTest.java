package com.otx.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.TopicListing;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaDiscoveryTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Properties kafkaInfo() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("connection.max.idle.ms", 10000);
        properties.put("request.timeout.ms", 5000);
        return properties;
    }

    @Test
    public void discoveryKafkaClient() {
        Properties properties = kafkaInfo();

        try (AdminClient adminClient = KafkaAdminClient.create(properties)) {
            Collection<TopicListing> topicListings = adminClient
                    .listTopics(new ListTopicsOptions().timeoutMs(5000))
                    .listings()
                    .get();

            logger.info("topics = {}", topicListings);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }
}
