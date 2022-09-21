package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.model.Community;
import dev.jsedano.examples.redis.meetups.model.Member;
import dev.jsedano.examples.redis.meetups.repository.CommunityRepostory;
import dev.jsedano.examples.redis.meetups.repository.MemberRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/member")
public class MemberControllerV1 {

    @Autowired
    MemberRepostory repo;

    @GetMapping("all")
    Iterable<Member> all() {
        return repo.findAll();
    }

    @PostMapping("{member}")
    void create(@RequestBody Member member) {
        member.setId(null);
        repo.save(member);
    }

}
