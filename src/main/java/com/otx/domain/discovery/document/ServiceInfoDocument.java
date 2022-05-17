package com.otx.domain.discovery.document;

import com.otx.domain.discovery.dto.Topic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document("message_service_info_detail")
public class ServiceInfoDocument {

    @Id
    private String oid;
    private String serviceInfoOid;
    private String host;
    private boolean isAlive;
    private String error;
    private Collection<Topic> topics;
}
