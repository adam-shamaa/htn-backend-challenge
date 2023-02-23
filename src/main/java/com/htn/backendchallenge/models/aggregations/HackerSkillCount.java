package com.htn.backendchallenge.models.aggregations;

import lombok.Data;

@Data
public class HackerSkillCount {

  private final String skill;
  private final long count;
}
