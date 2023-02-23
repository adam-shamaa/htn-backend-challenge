package com.htn.backendchallenge.repositories;

import com.htn.backendchallenge.models.Hacker;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.repository.Repository;

public interface HackerRepository extends Repository<Hacker, Long> {
  Set<Hacker> findAll();
  Optional<Hacker> findById(long hackerId);
  Hacker save(Hacker hacker);
}
