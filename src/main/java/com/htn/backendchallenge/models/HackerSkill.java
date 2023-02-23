package com.htn.backendchallenge.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
@Table(name = "hackerskill")
public class HackerSkill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String skill;

  @Column(nullable = false)
  private int ratingTen;

  @ManyToOne(targetEntity = Hacker.class, fetch = FetchType.LAZY)
  private Hacker hacker;
}
