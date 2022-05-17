package com.otx.domain.discovery.repo;

import com.otx.domain.discovery.document.MessagingServiceInfoDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessagingServiceInfoRepository extends ReactiveMongoRepository<String, MessagingServiceInfoDocument> {
}
