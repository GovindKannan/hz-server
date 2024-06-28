package com.gk.spring.hazelcast.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CacheData {
    private String instanceName;
    private String cacheName;
    private String key;
    private String value;
}
