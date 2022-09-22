package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.model.Event;
import dev.jsedano.examples.redis.meetups.model.Member;
import dev.jsedano.examples.redis.meetups.repository.MemberRepostory;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
public class MemberControllerV1 {

  @Autowired MemberRepostory repo;

  @GetMapping("all")
  Iterable<Member> all() {
    return repo.findAll();
  }

  @GetMapping("{id}")
  Optional<Member> byId(@PathVariable String id) {
    return repo.findById(id);
  }

  @GetMapping("technologies")
  Iterable<Member> byAnyTechnologies(@RequestParam("technologies") Set<String> technologies) {
    return repo.findByTechnologies(technologies);
  }

  @GetMapping("communities")
  Iterable<Member> byAnyCommunities(@RequestParam("communities") Set<String> communities) {
    return repo.findByCommunities(communities);
  }
}
