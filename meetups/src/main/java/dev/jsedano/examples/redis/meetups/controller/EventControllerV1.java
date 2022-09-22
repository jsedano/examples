package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.model.Event;
import dev.jsedano.examples.redis.meetups.repository.EventRepostory;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event")
public class EventControllerV1 {

  @Autowired EventRepostory repo;

  @GetMapping("all")
  Iterable<Event> all() {
    return repo.findAll();
  }

  @GetMapping("{id}")
  Optional<Event> byId(@PathVariable String id) {
    return repo.findById(id);
  }

  @GetMapping("communityId")
  Iterable<Event> byCommunityId(@RequestParam String communityId) {
    return repo.findByCommunityId(communityId);
  }


}
