package com.gk.spring.hazelcast.model;

import com.hazelcast.cluster.Cluster;
import com.hazelcast.core.HazelcastInstance;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@Builder
public class HazelcastServerManagerResponse {
    private String statusValue;
    private boolean status;
    private Set<Cluster> clusters;
    private Set<String> instanceNames;
    private HazelcastInstance hazelcastInstance;

}
