package com.htn.backendchallenge.services;

import com.htn.backendchallenge.models.Hacker;
import com.htn.backendchallenge.models.aggregations.HackerSkillCount;
import com.htn.backendchallenge.repositories.HackerRepository;
import com.htn.backendchallenge.repositories.HackerSkillRepository;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HackerService {

  private final HackerRepository hackerRepository;
  private final HackerSkillRepository hackerSkillRepository;

  public Set<Hacker> getAllHackers() {
    return this.hackerRepository.findAll();
  }

  public Optional<Hacker> getHackerById(long hackerId) {
    return this.hackerRepository.findById(hackerId);
  }

  public Set<HackerSkillCount> getSkillsByCount(int minCount, int maxCount) {
    return this.hackerSkillRepository.fetchHackerSkillsByCount(
        minCount,
        maxCount
      );
  }

  public Hacker save(Hacker hacker) {
    return this.hackerRepository.save(hacker);
  }
}
