package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Event;

public interface EventRepostory extends RedisDocumentRepository<Event, String> {}
