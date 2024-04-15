package com.example.demo.HelloWorld;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/helloworld")
    public ResponseEntity<HelloWorld> HelloWorld(
            @RequestParam(value = "name", defaultValue = "Hello") String name,
            @RequestParam(value = "lastname", defaultValue = "World") String lastname,
            @RequestParam(value = "age", defaultValue = "0") int age) {

        HelloWorld hello = new HelloWorld(name, lastname, age);
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }


}
