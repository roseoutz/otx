package com.otx.domain.discovery.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@Document("ms_info")
public class ServiceInfoDocument {

    @Id
    private String oid;
    private String type;
    private String host;
    private boolean isAlive;
    private String error;
    private long lastConnectionTime;

}
