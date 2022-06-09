package com.otx.domain.discovery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("topic_connection")
public class TopicConnectionDocument {

    @Id
    private String oid;
    private String topicOid;
    private List<String> onSuccess;
    private List<String> onFailure;
}
