package dev.jsedano.examples.redis.meetups.repository;

import dev.jsedano.examples.redis.meetups.model.Community;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface CommunityRepostory extends RedisDocumentRepository<Community, String> {
}
