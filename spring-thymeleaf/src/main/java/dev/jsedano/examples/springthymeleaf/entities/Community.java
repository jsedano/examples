package dev.jsedano.examples.springthymeleaf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Community {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long communityId;

  @NotNull(message = "name should not be null")
  @NotEmpty(message = "name should not be empty")
  private String name;

  @NotNull(message = "leader should not be null")
  @NotEmpty(message = "leader should not be empty")
  private String leader;

  private Type type;

  @NotNull(message = "members should not be null")
  @Min(value = 1, message = "members should be at least 1")
  private Integer members;
}
