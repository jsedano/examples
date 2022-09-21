package dev.jsedano.examples.redis.meetups.model;


import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import com.redis.om.spring.annotations.Searchable;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Document
public class Member {

    @Id
    @Indexed
    private String id;

    @Indexed @NonNull
    private String username;

    @Searchable
    @NonNull
    private String email;

    @Searchable
    private String tShirtSize;

    @Indexed
    @NonNull
    private Set<String> technologies;

    @Indexed
    @NonNull
    private Set<String> communities;

}
