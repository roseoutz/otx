package com.otx.domain.discovery.dto;

import lombok.Getter;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Getter
public class ServiceInfo {

    private final String host;
    private final boolean isAlive;
    private final Throwable error;
    private final Collection<Topic> topics;

    private ServiceInfo(Builder builder) {
        this.host = builder.host;
        this.isAlive = builder.isAlive;
        this.error = builder.error;
        this.topics = Collections.unmodifiableCollection(builder.topics);
    }

    private ServiceInfo(String host, boolean isAlive, Throwable throwable, Collection<Topic> topics) {
        this.host = host;
        this.isAlive = isAlive;
        this.error = throwable;
        this.topics = Collections.unmodifiableCollection(topics);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String host;
        private boolean isAlive;
        private Throwable error;
        private Collection<Topic> topics = new ArrayList<>();

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder isAlive(boolean isAlive) {
            this.isAlive = isAlive;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        public Builder topic(Topic topic) {
            this.topics.add(topic);
            return this;
        }

        public Builder topic(Collection<TopicListing> topicListings) {
            topicListings.stream()
                    .map(topicListing -> new Topic(topicListing.name(), topicListing.topicId().toString()))
                    .forEach(topic -> this.topics.add(topic));

            return this;
        }

        public ServiceInfo build() {
            return new ServiceInfo(host, isAlive, error, topics);
        }
    }
}
