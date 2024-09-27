package org.devdogs.rmpmicroservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// REST controller for RMP professor services
@RestController
@RequestMapping("/api/professor-service")
public class RMPProfessorController {
    //placeholder GET mapping to demonstrate endpoint functionality
    @GetMapping("/professors")
    public String getProfessors() {
        //For now just return a static message
        return "List of professors will go here";
    }
}