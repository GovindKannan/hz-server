package com.gk.spring.hazelcast.service;

import com.gk.spring.hazelcast.model.CacheManagerResponse;
import com.gk.spring.hazelcast.model.HazelcastServerManagerResponse;
import com.hazelcast.core.HazelcastInstance;

import java.io.IOException;

public interface HazelcastServerAdmin {

    public HazelcastInstance start() throws IOException;
    public boolean stop(String id);

    public HazelcastServerManagerResponse getClusterNames();
    public HazelcastServerManagerResponse getAllInstances();
    public HazelcastServerManagerResponse getInstanceDetails(String instanceName);
}
