package dev.jsedano.examples.springthymeleaf.dao;

import dev.jsedano.examples.springthymeleaf.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {}
