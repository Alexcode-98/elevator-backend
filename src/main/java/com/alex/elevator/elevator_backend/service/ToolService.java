package com.alex.elevator.elevator_backend.service;

import com.alex.elevator.elevator_backend.entities.Tool;
import com.alex.elevator.elevator_backend.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolService {

    private final ToolRepository toolRepository;


    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public Tool saveTool (Tool tool) {
        return toolRepository.save(tool);
    }

}
