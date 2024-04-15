package com.example.demo.HelloWorld;
import lombok.Data;

@Data
public class HelloWorld {
     private String name;
     private String lastname;
     private int age;


    public HelloWorld(String name, String lastname, int age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }



}
