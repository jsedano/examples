package dev.jsedano.examples.springthymeleaf.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long projectId;

  private String name;
  private String stage; // NOTSTARTED COMPLETED INPROGRESS
  private String description;
  private Integer age;
}
