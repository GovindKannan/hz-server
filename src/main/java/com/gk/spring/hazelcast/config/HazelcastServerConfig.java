package com.gk.spring.hazelcast.config;

import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.gk.spring.hazelcast.service.HazelcastCacheManager;
import com.gk.spring.hazelcast.service.HazelcastServerAdmin;
import com.hazelcast.config.Config;
import com.hazelcast.config.YamlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class HazelcastServerConfig {

    @Autowired
    private HazelcastServerAdmin hazelcastServerAdmin;

    @Bean
    public HazelcastInstance hazelcastInstance() throws IOException {
        return hazelcastServerAdmin.start();
    }
}
