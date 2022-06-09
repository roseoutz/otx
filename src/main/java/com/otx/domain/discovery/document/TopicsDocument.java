package com.otx.domain.discovery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ms_topics")
public class TopicsDocument {

    @Id
    private String oid;
    private String serviceInfoOid;
    private String name;
    private String id;
}
