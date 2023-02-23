package com.htn.backendchallenge.controllers;

import com.htn.apicodegen.HackersApi;
import com.htn.apicodegen.model.*;
import com.htn.backendchallenge.form.validator.HackerValidator;
import com.htn.backendchallenge.mappers.CommonMapper;
import com.htn.backendchallenge.mappers.HackerMapper;
import com.htn.backendchallenge.models.Hacker;
import com.htn.backendchallenge.services.HackerService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;

@Controller
@RequiredArgsConstructor
public class HackersController implements HackersApi {

  private final HackerMapper hackersMapper;
  private final HackerService hackerService;
  private final CommonMapper commonMapper;

  @Override
  public ResponseEntity<GetHackersResponseDto> hackersGet() {
    List<HackerDto> hackerDtoList = hackerService
      .getAllHackers()
      .stream()
      .map(hackersMapper::hackerToHackerDTO)
      .toList();

    return ResponseEntity
      .ok()
      .body(new GetHackersResponseDto().hackers(hackerDtoList));
  }

  @Override
  public ResponseEntity<GetHackerResponseDto> hackersHackerUUIDGet(
    Integer hackerUUID
  ) {
    Optional<Hacker> hackerOptional = hackerService.getHackerById(hackerUUID);

    return hackerOptional.isPresent()
      ? ResponseEntity.ok(
        new GetHackerResponseDto()
          .hacker(hackersMapper.hackerToHackerDTO(hackerOptional.get()))
      )
      : ResponseEntity.notFound().build();
  }

  @Override
  public ResponseEntity<GetHackerResponseDto> hackersHackerUUIDPatch(
    Integer hackerUUID,
    HackerDto hackerDto
  ) {
    BindingResult errors = new BeanPropertyBindingResult(
      hackerDto,
      "HackerDTOForm"
    );
    ValidationUtils.invokeValidator(new HackerValidator(), hackerDto, errors);

    if (errors.hasErrors()) {
      return new ResponseEntity(
        new FormErrorsDto()
          .errors(
            errors
              .getFieldErrors()
              .stream()
              .map(commonMapper::fieldErrorToFieldErrorDto)
              .toList()
          ),
        HttpStatus.BAD_REQUEST
      );
    }

    Optional<Hacker> hacker = this.hackerService.getHackerById(hackerUUID);
    if (hacker.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Hacker updatedHacker =
      this.hackersMapper.updateHackerFromDto(hackerDto, hacker.get());

    return ResponseEntity.ok(
      new GetHackerResponseDto()
        .hacker(
          hackersMapper.hackerToHackerDTO(
            this.hackerService.save(updatedHacker)
          )
        )
    );
  }

  @Override
  public ResponseEntity<GetHackerSkillsResponseDto> hackersSkillsGet(
    Integer minFrequency,
    Integer maxFrequency
  ) {
    List<HackerSkillFrequencyDto> hackerSkillFrequencyDtoList = hackerService
      .getSkillsByCount(minFrequency, maxFrequency)
      .stream()
      .map(hackersMapper::hackerSkillCountToFrequencyDto)
      .toList();

    return ResponseEntity.ok(
      new GetHackerSkillsResponseDto().skills(hackerSkillFrequencyDtoList)
    );
  }
}
