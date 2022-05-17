package com.otx.domain.discovery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("message_service_info")
public class MessagingServiceInfoDocument {

    @Id
    private String oid;

    private String type;

    private String host;

}
