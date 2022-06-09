package com.otx.domain.discovery.repo;

import com.otx.domain.discovery.document.ServiceInfoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MessagingServiceInfoRepository extends ReactiveMongoRepository<ServiceInfoDocument, String> {

    Mono<ServiceInfoDocument> findByHost(String host);
}
