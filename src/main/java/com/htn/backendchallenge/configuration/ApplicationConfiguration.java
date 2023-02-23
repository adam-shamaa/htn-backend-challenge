package com.htn.backendchallenge.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.htn.backendchallenge.repositories")
public class ApplicationConfiguration {}
