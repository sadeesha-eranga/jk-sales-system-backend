package com.icbt.jksalessystem.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: sadeesha
 * Date: 2021-02-20
 */
@Log4j2
@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/branches")
public class BranchController {

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello World!";
    }
}
