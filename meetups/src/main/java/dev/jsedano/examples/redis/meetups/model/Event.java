package dev.jsedano.examples.redis.meetups.model;


import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
public class Event {

    @Id
    @Indexed
    private String id;

    @Indexed
    @NonNull
    private String communityId;

    @Searchable @NonNull
    private String title;

    @NonNull
    @Indexed
    private LocalDateTime date;
}
