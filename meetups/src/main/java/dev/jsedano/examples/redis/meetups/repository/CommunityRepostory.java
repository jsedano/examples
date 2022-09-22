package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Community;

import java.util.Set;

public interface CommunityRepostory extends RedisDocumentRepository<Community, String> {


    Iterable<Community> findByTechnologies(Set<String> technologies);

}
