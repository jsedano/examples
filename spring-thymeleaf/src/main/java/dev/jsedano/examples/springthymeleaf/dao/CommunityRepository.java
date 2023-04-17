package dev.jsedano.examples.springthymeleaf.dao;

import org.springframework.data.repository.CrudRepository;

public interface CommunityRepository
    extends CrudRepository<dev.jsedano.examples.springthymeleaf.entities.Community, Long> {}
