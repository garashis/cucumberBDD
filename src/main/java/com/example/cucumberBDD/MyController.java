package com.example.cucumberBDD;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MyController {

    @GetMapping("/getTime")
    public String getTime(){
        return LocalDateTime.now().toString();
    }
}
