package com.gk.spring.hazelcast.controller;

import com.gk.spring.hazelcast.model.HazelcastServerManagerResponse;
import com.gk.spring.hazelcast.service.HazelcastServerAdmin;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping("/v1/server")
public class HazelcastServerAdminController {

    @Autowired
    HazelcastServerAdmin hazelcastServerAdmin;

    @PostMapping("/instance")
    public ResponseEntity<HazelcastInstance> start() throws IOException {
        return ResponseEntity.ok(hazelcastServerAdmin.start());
    }

    @DeleteMapping("/instance/{id}")
    public ResponseEntity<Boolean> stop(@PathVariable("id") String id) throws IOException {
        return ResponseEntity.ok(hazelcastServerAdmin.stop(id));
    }

    @GetMapping("/clusters")
    public ResponseEntity<HazelcastServerManagerResponse> getClusterNames() throws IOException {
        return ResponseEntity.ok(hazelcastServerAdmin.getClusterNames());
    }

    @GetMapping("/clusters/members/")
    public ResponseEntity<HazelcastServerManagerResponse> getClusterMembers() throws IOException {
        return ResponseEntity.ok(hazelcastServerAdmin.getAllInstances());
    }

    @GetMapping("/clusters/members/{instance-name}")
    public ResponseEntity<HazelcastServerManagerResponse> getClusterMembers(@PathVariable("instance-name") String instanceName) throws IOException {
        return ResponseEntity.ok(hazelcastServerAdmin.getInstanceDetails(instanceName));
    }
}
