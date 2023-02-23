package com.htn.backendchallenge.mappers;

import com.htn.apicodegen.model.HackerDto;
import com.htn.apicodegen.model.HackerSkillDto;
import com.htn.apicodegen.model.HackerSkillFrequencyDto;
import com.htn.backendchallenge.models.Hacker;
import com.htn.backendchallenge.models.HackerSkill;
import com.htn.backendchallenge.models.aggregations.HackerSkillCount;
import org.mapstruct.*;

@Mapper
public interface HackerMapper {
  @Mapping(target = ".", source = "user")
  HackerDto hackerToHackerDTO(Hacker hacker);

  HackerSkillDto hackerSkillToHackerSkillDTO(HackerSkill hackerSkill);

  @Mapping(target = "frequency", source = "count")
  HackerSkillFrequencyDto hackerSkillCountToFrequencyDto(
    HackerSkillCount hackerSkillCount
  );

  @BeanMapping(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
  )
  @Mapping(target = "user.name", source = "name")
  @Mapping(target = "user.email", source = "email")
  @Mapping(target = "skills", source = "skills")
  Hacker updateHackerFromDto(HackerDto hackerDto, @MappingTarget Hacker hacker);

  @AfterMapping()
  default void updateHackerSkillForeignKey(@MappingTarget Hacker hacker) {
    if (hacker.getSkills() != null) {
      hacker.getSkills().forEach(skill -> {
        skill.setHacker(hacker);
      });
    }
  }
}
