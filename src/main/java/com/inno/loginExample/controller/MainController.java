package com.inno.loginExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main() {
        return "Main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
