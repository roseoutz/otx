package com.otx.domain.discovery.service;

import com.otx.domain.discovery.dto.MessageDiscoveryResult;
import com.otx.domain.discovery.dto.DiscoveryRequest;

import java.util.List;

public interface DiscoveryService {

    MessageDiscoveryResult discoveryService(DiscoveryRequest discoveryRequest);

    MessageDiscoveryResult discoveryService(List<DiscoveryRequest> discoveryRequestList);
}
