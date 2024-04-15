package com.ilv.helloworld;

import com.ilv.helloworld.security.Roles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @RolesAllowed(Roles.Admin)
    public ResponseEntity<HelloWorld> hello(@RequestParam(value = "name", defaultValue = "World") String name,
                                            @RequestParam(value = "lastname", defaultValue = "World") String lastname,
                                            @RequestParam(value = "age", defaultValue = "0") Number age) {
        HelloWorld helloWorld = new HelloWorld(name, lastname, age);
        return new ResponseEntity<>(helloWorld, HttpStatus.OK);
    }

}