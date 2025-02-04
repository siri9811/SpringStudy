package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI 컨트롤러! json 변환
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}
