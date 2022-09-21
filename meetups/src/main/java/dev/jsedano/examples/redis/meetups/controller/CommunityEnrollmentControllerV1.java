package dev.jsedano.examples.redis.meetups.controller;

import dev.jsedano.examples.redis.meetups.repository.CommunityRepostory;
import dev.jsedano.examples.redis.meetups.repository.MemberRepostory;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/communityEnrollment/")
@Log
public class CommunityEnrollmentControllerV1 {

    @Autowired
    CommunityRepostory communityRepostory;

    @Autowired
    MemberRepostory memberRepostory;

    @PutMapping("{memberId}/{communityId}")
    void addToCommunity(@PathVariable String memberId,@PathVariable String communityId) {
        var community = communityRepostory.findById(communityId).get();
        var member = memberRepostory.findById(memberId).get();
        Optional.ofNullable(community.getMembers()).ifPresentOrElse(s -> s.add(memberId), ()-> community.setMembers(Set.of(memberId)));
        Optional.ofNullable(member.getCommunities()).ifPresentOrElse(s -> s.add(communityId), ()-> member.setCommunities(Set.of(communityId)));
        communityRepostory.save(community);
        memberRepostory.save(member);

    }

}
