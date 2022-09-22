package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.model.Community;
import dev.jsedano.examples.redis.meetups.model.Member;
import dev.jsedano.examples.redis.meetups.repository.CommunityRepostory;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/community")
public class CommunityControllerV1 {


  @Autowired CommunityRepostory repo;

  @GetMapping("all")
  Iterable<Community> all() {
    return repo.findAll();
  }

  @GetMapping("{id}")
  Optional<Community> byId(@PathVariable String id) {
    return repo.findById(id);
  }

  @GetMapping("technologies")
  Iterable<Community> byAnyTechnologies(@RequestParam("technologies") Set<String> technologies) {
    return repo.findByTechnologies(technologies);
  }
}
