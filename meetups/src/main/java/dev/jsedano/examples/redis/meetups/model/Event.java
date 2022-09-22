package dev.jsedano.examples.redis.meetups.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
@Builder
public class Event {

  @Id @Indexed private String id;

  @Indexed @NonNull private String communityId;

  @Searchable @NonNull private String title;

  @NonNull @Indexed private LocalDateTime date;
}
