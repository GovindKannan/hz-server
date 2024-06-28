package com.gk.spring.hazelcast.service;

import com.gk.spring.hazelcast.model.CacheData;
import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface HazelcastCacheManager {

    public Map<String, String> getCacheNames();
    public Map<String, Map<String, Set<Object>>> getAllCacheKeys(String cacheName);
    public CacheManagerResponse getAllCacheValues(String cacheName);
    public CacheManagerResponse getCacheValue(CacheData cacheData);
    public CacheManagerResponse evictData(CacheData cacheData;
    public CacheManagerResponse evictAllData(String cacheName);


}
