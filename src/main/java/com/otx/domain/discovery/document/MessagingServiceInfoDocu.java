package com.otx.domain.discovery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.monitor.ServerInfo;

@Document("messaage_service_info")
public class MessagingServiceInfoDocu {

    @Id
    private String oid;

    private String type;

    private String host;

    private ServerInfo serverInfo;

}
