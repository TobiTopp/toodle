package com.ilv.helloworld;

import jakarta.annotation.sql.DataSourceDefinition;
import lombok.Data;

@Data
public class HelloWorld {
    private String name;
    private String lastname;
    private Number age;

    public HelloWorld(String name, String lastname, Number age) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

}
