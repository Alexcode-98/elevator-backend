package com.alex.elevator.elevator_backend.config;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.alex.elevator.elevator_backend")
@EnableJpaRepositories("com.alex.elevator.elevator_backend")
@EnableTransactionManagement
public class DomainConfig {
}
