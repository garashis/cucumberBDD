package com.example.cucumberBDD;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class MyController {

    @GetMapping("/getTime")
    public String getTime(){
        return LocalDateTime.now().toString();
    }

    @PostMapping("/event")
    public String event(@RequestBody EventRequest req){
        System.out.println(req.getEvent());
        return LocalDateTime.now().toString();
    }
}
