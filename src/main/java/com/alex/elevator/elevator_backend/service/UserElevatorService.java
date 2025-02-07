package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.dtos.ClientRegistered;
import com.alex.elevator.elevator_backend.dtos.UserDto;
import com.alex.elevator.elevator_backend.entities.UserElevator;
import com.alex.elevator.elevator_backend.repository.UserElevatorRepository;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserElevatorService {

    private final UserElevatorRepository userElevatorRepository;


    @Autowired
    public UserElevatorService(UserElevatorRepository userElevatorRepository) {
        this.userElevatorRepository = userElevatorRepository;
    }

    public UserElevator saveUserElevator (UserDto userElevator) {
        UserElevator entity = new UserElevator();
        BeanUtils.copyProperties(userElevator, entity, "roles");
        if (entity.getRegistrationDate()==null){
            entity.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
        }
        return userElevatorRepository.save(entity);
    }

}
