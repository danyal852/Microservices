package com.microservices2.controller;

import com.microservices2.client.MessegeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/ms2")
public class MicroservicesController {
    @Autowired
    private MessegeClient messegeClient;
    //http://localhost:8082/api/v1/ms2/call
    @GetMapping("/call")
    public String getMessageFromMs1() {
        return messegeClient.getMessage();
    }
}
