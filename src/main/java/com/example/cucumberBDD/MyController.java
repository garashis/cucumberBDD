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

    @PostMapping("/myEndpoint")
    public String event(@RequestBody Product req){
//        System.out.println("MyController req >>> " + req);
        return LocalDateTime.now().toString();
    }
}
