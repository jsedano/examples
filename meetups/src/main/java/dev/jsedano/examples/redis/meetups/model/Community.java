package dev.jsedano.examples.redis.meetups.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import java.util.Set;
import lombok.*;
import org.springframework.data.annotation.Id;

@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
@Builder
public class Community {
  @Id @Indexed private String id;

  @Indexed @NonNull private String name;

  @Searchable @NonNull private String description;

  @Searchable @NonNull private Set<String> technologies;

  @Indexed @NonNull private Set<String> members;
}
