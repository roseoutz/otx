package com.otx.domain.discovery.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("orchestration_info")
public class OrchestrationInfoDocument {

    @Id
    private String oid;
    private String orchestrationName;
    private List<String> serviceList;
    private long updateAt;

}
