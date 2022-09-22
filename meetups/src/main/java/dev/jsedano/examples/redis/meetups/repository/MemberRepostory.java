package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Member;

public interface MemberRepostory extends RedisDocumentRepository<Member, String> {}
