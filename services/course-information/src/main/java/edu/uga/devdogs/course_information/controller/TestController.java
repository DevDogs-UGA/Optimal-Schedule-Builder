package edu.uga.devdogs.course_information.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public String testDatabase() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Database connection is working!";
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}