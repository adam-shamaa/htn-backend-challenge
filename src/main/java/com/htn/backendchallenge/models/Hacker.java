package com.htn.backendchallenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = { "user" })
@Table(name = "hacker")
public class Hacker {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(
    targetEntity = HackerSkill.class,
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    mappedBy = "hacker",
    orphanRemoval = true
  )
  private List<HackerSkill> skills;

  @NotNull
  @Column(length = 100)
  private String company;
}
