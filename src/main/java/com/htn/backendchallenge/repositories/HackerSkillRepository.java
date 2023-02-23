package com.htn.backendchallenge.repositories;

import com.htn.backendchallenge.models.HackerSkill;
import com.htn.backendchallenge.models.aggregations.HackerSkillCount;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface HackerSkillRepository extends Repository<HackerSkill, Long> {
  @Query(
    value = """
        SELECT new com.htn.backendchallenge.models.aggregations.HackerSkillCount(s.skill, COUNT(*))
        FROM HackerSkill s
        GROUP BY s.skill
        HAVING COUNT(*) BETWEEN :minCount AND :maxCount
    """
  )
  Set<HackerSkillCount> fetchHackerSkillsByCount(
    @Param("minCount") int minCount,
    @Param("maxCount") int maxCount
  );
}
