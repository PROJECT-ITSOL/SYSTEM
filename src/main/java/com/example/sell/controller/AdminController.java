package com.example.sell.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ResponseStatus(HttpStatus.)
public class AdminController {

    @CrossOrigin
    @GetMapping("/")
    public String homeAdmin(){
        return "admin";
    }

}
