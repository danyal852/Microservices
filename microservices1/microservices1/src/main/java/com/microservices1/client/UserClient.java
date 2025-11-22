package com.microservices1.client;

import com.microservices1.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "AUTH-SERVICE",
        url = "http://localhost:8080/api/v1/auth"
)
public interface UserClient {
    @GetMapping("/getuser")
    public User getUserByName(@RequestParam("username") String name, @RequestHeader("Authorization") String token);
}
