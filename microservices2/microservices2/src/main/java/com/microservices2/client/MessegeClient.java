package com.microservices2.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "MICROSERVICES1")
public interface MessegeClient {
    @GetMapping("/api/v1/message/welcome")
    public String getMessage();
}
