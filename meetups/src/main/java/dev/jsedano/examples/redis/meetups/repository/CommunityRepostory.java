package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Community;

public interface CommunityRepostory extends RedisDocumentRepository<Community, String> {}
