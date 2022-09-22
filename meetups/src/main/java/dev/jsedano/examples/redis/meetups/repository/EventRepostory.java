package dev.jsedano.examples.redis.meetups.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.jsedano.examples.redis.meetups.model.Event;
import dev.jsedano.examples.redis.meetups.model.Member;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Set;

public interface EventRepostory extends RedisDocumentRepository<Event, String> {

    Iterable<Event> findByCommunityId(String communityId);

}
