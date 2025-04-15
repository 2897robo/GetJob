package com.example.demo_gradle.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    private final int id;
    private final String name;
    private final int age;

    public UserDto(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @JsonProperty("id")
    public int id() {
        return id;
    }

    @JsonProperty("name")
    public String name() {
        return name;
    }

    @JsonProperty("age")
    public int age() {
        return age;
    }
}
