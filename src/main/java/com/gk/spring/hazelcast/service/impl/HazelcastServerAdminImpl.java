package com.gk.spring.hazelcast.service.impl;

import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.gk.spring.hazelcast.model.HazelcastServerManagerResponse;
import com.gk.spring.hazelcast.service.HazelcastServerAdmin;
import com.hazelcast.config.Config;
import com.hazelcast.config.YamlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HazelcastServerAdminImpl implements HazelcastServerAdmin {

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    Environment environment;

    @Override
    public HazelcastInstance start()throws IOException {
        Resource resource = resourceLoader.getResource("classpath:hazelcast-"+environment.getProperty("stripe")+".yml");
        Config config = new YamlConfigBuilder(resource.getInputStream()).build();
        config.setInstanceName(config.getInstanceName()+ UUID.randomUUID());
        return Hazelcast.newHazelcastInstance(config);
    }

    @Override
    public boolean stop(String instanceName) {
        HazelcastInstance instance = Hazelcast.getHazelcastInstanceByName(instanceName);
        if(Objects.nonNull(instance)){
            log.info("Stopping instance: {}, instance-details: {}", instance.getName(), instance);
            instance.shutdown();
        }
        return true;
    }

    @Override
    public HazelcastServerManagerResponse getClusterNames() {
        return HazelcastServerManagerResponse.builder()
                .statusValue("Cluster details fetched")
                .status(true)
                .clusters(Hazelcast.getAllHazelcastInstances().stream()
                        .map(hazelcastInstance -> hazelcastInstance.getCluster()).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public HazelcastServerManagerResponse getAllInstances() {
        return HazelcastServerManagerResponse.builder()
                .instanceNames(Hazelcast.getAllHazelcastInstances().stream()
                        .map(HazelcastInstance::getName).collect(Collectors.toSet()))
                .build();
//
//                HazelcastServerManagerResponse.builder()
//                .statusValue("Cluster details fetched")
//                .status(true)
//                .instancePerCluster(Hazelcast.getAllHazelcastInstances().stream()
//                        .collect(Collectors.groupingBy(
//                                HazelcastInstance::getClusterName,
//                                Collectors.mapping(HazelcastInstance::getName, Collectors.toSet())
//                        ));)
//                .build();
    }

    @Override
    public HazelcastServerManagerResponse getInstanceDetails(String instanceName) {
        return HazelcastServerManagerResponse.builder()
                .status(true)
                .statusValue("Fetched Instance Details")
                .hazelcastInstance(Hazelcast.getHazelcastInstanceByName(instanceName))
                .build();
    }
}
