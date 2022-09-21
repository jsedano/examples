package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.model.Community;
import dev.jsedano.examples.redis.meetups.repository.CommunityRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/community")
public class CommunityControllerV1 {

    @Autowired
    CommunityRepostory repo;

    @GetMapping("all")
    Iterable<Community> all() {
        return repo.findAll();
    }

    @GetMapping("{id}")
    Optional<Community> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping("{community}")
    void create(@RequestBody Community community) {
        community.setId(null);
        repo.save(community);
    }

}
