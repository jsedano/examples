package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Member;

import java.util.Set;

public interface MemberRepostory extends RedisDocumentRepository<Member, String> {

    Iterable<Member> findByTechnologies(Set<String> technologies);

    Iterable<Member> findByCommunities(Set<String> communities);

}

