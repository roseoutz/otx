package com.otx.domain.discovery.repo;

import com.otx.domain.discovery.document.MessagingServiceInfoDocu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessagingServiceInfoRepository extends ReactiveMongoRepository<String, MessagingServiceInfoDocu> {
}
