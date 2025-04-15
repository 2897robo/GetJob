package com.example.demo_gradle.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private String password;
    private int age;

    public User(int id, String name, String pw, int age) {
        this.id = id;
        this.name = name;
        this.password = pw;
        this.age = age;
    }
}
