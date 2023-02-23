package com.htn.backendchallenge.form.validator;

import com.htn.apicodegen.model.HackerDto;
import com.htn.apicodegen.model.HackerSkillDto;
import java.util.stream.Collectors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class HackerValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return HackerDto.class == clazz;
  }

  @Override
  public void validate(Object target, Errors errors) {
    HackerDto hacker = (HackerDto) target;

    if (hacker.getSkills() != null) {
      this.validateSkills(target, errors);
    }
  }

  private void validateSkills(Object target, Errors errors) {
    HackerDto hacker = (HackerDto) target;

    int numDistinctSkills = hacker
      .getSkills()
      .stream()
      .map(HackerSkillDto::getSkill)
      .collect(Collectors.toSet())
      .size();
    if (hacker.getSkills().size() != numDistinctSkills) {
      errors.rejectValue(
        "skills",
        "DUPLICATE_VALUES",
        "skills entries must have unique 'skill' field values"
      );
    }

    hacker.getSkills().forEach(skill -> {
      if (skill.getSkill() == null) {
        errors.rejectValue(
                "skills",
                "NULL_VALUE",
                "skills entries must have non-null 'skill' field value"
        );
      }

      if (skill.getRatingTen() == null) {
        errors.rejectValue(
                "skills",
                "NULL_VALUE",
                "skills entries musthave non-null 'ratingTen' field value"
        );
      }
    });
  }
}
