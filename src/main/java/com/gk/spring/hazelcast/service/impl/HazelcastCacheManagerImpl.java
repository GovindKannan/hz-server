package com.gk.spring.hazelcast.service.impl;

import com.gk.spring.hazelcast.model.CacheData;
import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.gk.spring.hazelcast.service.HazelcastCacheManager;
import com.hazelcast.core.DistributedObject;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HazelcastCacheManagerImpl implements HazelcastCacheManager {

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, HazelcastInstance> getAllHazelcastInstances() {
        return applicationContext.getBeansOfType(HazelcastInstance.class);
    }

    @Override
    public Map<String, String> getCacheNames() {
        Map<String,String > cacheNameMap = new HashMap<>();
        getAllHazelcastInstances().entrySet().stream()
            .forEach(entry->{
                entry.getValue().getDistributedObjects().stream()
                    .filter(distributedObject -> distributedObject.getServiceName().equals("hz:impl:mapService"))
                    .forEach(distributedObject -> {
                        cacheNameMap.put(entry.getKey(), distributedObject.getName());
                    });
            });
        return cacheNameMap;
    }

    @Override
    public Map<String, Map<String, Set<Object>>> getAllCacheKeys(String cacheName) {
        return getAllHazelcastInstances().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getDistributedObjects().stream()
                                .filter(distributedObject -> (distributedObject.getServiceName().equals("hz:impl:mapService")
                                        && cacheName.equalsIgnoreCase(distributedObject.getName())))
                                .collect(Collectors.toMap(
                                        DistributedObject::getName,
                                        distributedObject -> ((IMap<Object, Object>) distributedObject).keySet()
                                ))
                ));
    }

    @Override
    public CacheManagerResponse getAllCacheValues(String cacheName) {
        return CacheManagerResponse.builder().build();
    }

    @Override
    public CacheManagerResponse getCacheValue(CacheData cacheData) {


        return CacheManagerResponse.builder()
                .build();
    }

    @Override
    public CacheManagerResponse evictData(String cacheName, String key) {
        return CacheManagerResponse.builder()
                .build();
    }

    @Override
    public CacheManagerResponse evictAllData(CacheData cacheData) {
        HazelcastInstance instance = getAllHazelcastInstances().get(cacheData.getInstanceName());
        IMap<Object, Object> map = instance.getMap(cacheData.getCacheName());
        if (map != null) {
            map.clear();
        }
        return CacheManagerResponse.builder()
                .build();
    }


}
