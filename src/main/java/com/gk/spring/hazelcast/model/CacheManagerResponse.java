package com.gk.spring.hazelcast.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CacheManagerResponse {
    private String statusValue;
    private boolean status;
    private List<CacheData> cacheNames;
    private List<CacheData> cacheKeys;
    private List<CacheData> cacheDataList;
    private CacheData cacheData;
    private int mapSize;

}
