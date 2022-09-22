package dev.jsedano.examples.redis.meetups.util;

import dev.jsedano.examples.redis.meetups.model.Community;
import dev.jsedano.examples.redis.meetups.model.Event;
import dev.jsedano.examples.redis.meetups.model.Member;
import dev.jsedano.examples.redis.meetups.repository.CommunityRepostory;
import dev.jsedano.examples.redis.meetups.repository.EventRepostory;
import dev.jsedano.examples.redis.meetups.repository.MemberRepostory;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class InfoLoader {

  @Autowired CommunityRepostory communityRepostory;

  @Autowired MemberRepostory memberRepostory;

  @Autowired EventRepostory eventRepostory;

  private String tshirtSizes[] = {"XS", "S", "M", "L", "XL"};

  private String techonlogies[] = {
    "java",
    "koclim",
    "nodejs",
    "typescript",
    "big data",
    "wordpress",
    "php",
    "go",
    "mongo",
    "redis",
    "javascript",
    "data science",
    "mulesoft"
  };

  private String places[] = {
    "guadalajara",
    "guanajuato",
    "cdmx",
    "yucatan",
    "acapulco",
    "japon",
    "hyrule",
    "tutitlan",
    "europa",
    "san diego"
  };

  private final Random random = new Random();

  private String description =
      "%s is changing the way we work, how we live, and even how we express ourselves. Understanding how %s works can help you be in control of your life.";

  public Set<String> getRandomTech() {
    var set = new HashSet<String>();
    int upperBound = random.nextInt(1, 5);
    for (int i = 0; i < upperBound; i++) {
      set.add(techonlogies[random.nextInt(techonlogies.length)]);
    }
    return set;
  }

  public void loadMembers() {
    try (BufferedReader br =
        new BufferedReader(new FileReader(new ClassPathResource("members.csv").getFile()))) {
      String line;
      while ((line = br.readLine()) != null) {
        memberRepostory.save(
            Member.builder()
                .username(line.substring(0, line.indexOf('@')))
                .email(line)
                .tShirtSize(tshirtSizes[random.nextInt(tshirtSizes.length)])
                .technologies(getRandomTech())
                .communities(Collections.emptySet())
                .build());
      }

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<String> loadEventNames() {
    List<String> eventNames = new ArrayList<String>();
    try (BufferedReader br =
        new BufferedReader(new FileReader(new ClassPathResource("events.csv").getFile()))) {
      String line;
      while ((line = br.readLine()) != null) {
        eventNames.add(line);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return eventNames;
  }

  public void loadCommunities() {
    for (String s : techonlogies) {
      communityRepostory.save(
          Community.builder()
              .name(String.format("%s %s", s, places[random.nextInt(places.length)]))
              .description(String.format(description, s, s))
              .technologies(Set.of(s))
              .members(Collections.emptySet())
              .build());
    }
  }

  public void addEnrollment() {
    var memberIterable = memberRepostory.findAll();
    List<String> memberIds =
        StreamSupport.stream(memberIterable.spliterator(), false)
            .map(Member::getId)
            .collect(Collectors.toList());
    var communityIterable = communityRepostory.findAll();
    List<String> communityIds =
        StreamSupport.stream(communityIterable.spliterator(), false)
            .map(Community::getId)
            .collect(Collectors.toList());

    for (String communityId : communityIds) {
      Community community = communityRepostory.findById(communityId).get();
      int maxMembers = random.nextInt(20, 50);
      community.setMembers(new HashSet<String>());
      for (int i = 0; i < maxMembers; i++) {
        String memberId = memberIds.get(random.nextInt(memberIds.size()));
        if (!community.getMembers().contains(memberId)) {
          community.getMembers().add(memberId);
          Member member = memberRepostory.findById(memberId).get();
          Optional.ofNullable(member.getCommunities())
              .ifPresentOrElse(
                  s -> s.add(communityId), () -> member.setCommunities(Set.of(communityId)));
          member.getTechnologies().addAll(community.getTechnologies());
          memberRepostory.save(member);
        }
      }

      communityRepostory.save(community);
    }
  }

  public void addEvents() {
    List<String> eventNames = loadEventNames();
    var communityIterable = communityRepostory.findAll();
    for (Community community : communityIterable) {
      int maxEvents = random.nextInt(10, 15);

      for (int i = 0; i < maxEvents; i++) {
        eventRepostory.save(
            Event.builder()
                .title(
                    String.format(
                        eventNames.get(random.nextInt(eventNames.size())),
                        community.getName().substring(0, community.getName().indexOf(' '))))
                .communityId(community.getId())
                .date(randomLocalDateTime())
                .build());
      }
    }
  }

  public LocalDateTime randomLocalDateTime() {
    var min = LocalDate.of(2022, 1, 1);
    var max = LocalDate.of(2023, 1, 1);
    LocalDate localDate = LocalDate.ofEpochDay(random.nextLong(min.toEpochDay(), max.toEpochDay()));
    return LocalDateTime.of(localDate, LocalTime.of(19, 00));
  }

  public void deleteAll() {
    communityRepostory.deleteAll();
    memberRepostory.deleteAll();
    eventRepostory.deleteAll();
  }

  public void load() {
    loadMembers();
    loadCommunities();
    addEnrollment();
    addEvents();
  }
}
