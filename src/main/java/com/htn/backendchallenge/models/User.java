package com.htn.backendchallenge.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = { "hacker" })
@Table(name = "user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column
  private String name;

  @NotNull
  @Column
  private String email;

  @Column
  @NotNull
  private String phone;

  @OneToOne(mappedBy = "user")
  private Hacker hacker;
}
