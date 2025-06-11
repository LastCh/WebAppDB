package ru.bmstu.WebAppDB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class StatusController {
    @GetMapping("/getStatus")
    public String getStatus() {
        return "Application is running";
    }
}
