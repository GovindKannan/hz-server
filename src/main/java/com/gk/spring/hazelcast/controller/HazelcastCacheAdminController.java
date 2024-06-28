package com.gk.spring.hazelcast.controller;

import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.gk.spring.hazelcast.service.HazelcastCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/v1/caches")
public class HazelcastCacheAdminController {

    @Autowired
    HazelcastCacheManager hazelcastCacheManager;

    @GetMapping("/names")
    public ResponseEntity<CacheManagerResponse> getCacheNames() {
        return ResponseEntity.ok(hazelcastCacheManager.getCacheNames());
    }

    @GetMapping("/{cache-name}/keys")
    public ResponseEntity<CacheManagerResponse> getCacheKeys(@PathVariable("cache-name")String cacheName) {
        return ResponseEntity.ok(hazelcastCacheManager.getCacheKeys(cacheName));
    }

    @GetMapping("/{cache-name}")
    public ResponseEntity<CacheManagerResponse> getAllCacheValues(@PathVariable("cache-name")String cacheName) {
        return ResponseEntity.ok(hazelcastCacheManager.getAllCacheValues(cacheName));
    }

    @GetMapping("/{cache-name}/keys/{cache-key}")
    public ResponseEntity<CacheManagerResponse> getCacheValue(@PathVariable("cache-name")String cacheName, @PathVariable("cache-key")String cacheKey) {
        return ResponseEntity.ok(hazelcastCacheManager.getCacheValue(cacheName,cacheKey));
    }

    @DeleteMapping("/{cache-name}/keys/{cache-key}")
    public ResponseEntity<CacheManagerResponse> evictData(@PathVariable("cache-name")String cacheName, @PathVariable("cache-key")String cacheKey) {
        return ResponseEntity.ok(hazelcastCacheManager.evictData(cacheName,cacheKey));
    }

    @DeleteMapping("/{cache-name}")
    public ResponseEntity<CacheManagerResponse> evictAllData(@PathVariable("cache-name")String cacheName) {
        return ResponseEntity.ok(hazelcastCacheManager.evictAllData(cacheName));
    }
}
