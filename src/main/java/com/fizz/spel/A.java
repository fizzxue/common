package com.fizz.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class A {

    @Value("#{'${server.port}' + T(com.fizz.spel.B).aa()}")
    private String name;

    @PostConstruct
    public void init() {
        System.out.println(this);
    }
}
