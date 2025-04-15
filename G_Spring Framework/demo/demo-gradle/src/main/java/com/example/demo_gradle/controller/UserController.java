package com.example.demo_gradle.controller;

import com.example.demo_gradle.domain.User;
import com.example.demo_gradle.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private final List<User> users = new ArrayList<> ();

    public UserController() {
        users.add(new User(1, "giuk", "secret", 26));
        users.add(new User(2, "chulsoo", "ch-pwpwpwpw", 60));
    }

    @GetMapping("/user")
    public List<UserDto> getUsers() {
        return users.stream()
                .map(user -> new UserDto(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .map(u -> ResponseEntity.ok(new UserDto(u.getId(), u.getName(), u.getAge())))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/user")
    public String addUser(@RequestBody UserDto userDto) {
        users.add(new User(userDto.id(), userDto.name(), "defaultPW", userDto.age()));
        return userDto.id() + " User added!";
    }

    @PutMapping("/user/{id}")
    public String updateUser(@PathVariable int id, @RequestBody UserDto updatedDto) {
        for(User u : users) {
            if(u.getId() == id) {
                u.setAge(updatedDto.age());
                u.setPassword("UpdatedPWPWPW");
                return id + " User updated!";
            }
        }
        return "User not found";
    }

    @PatchMapping("/user/{id}/age")
    public String patchUserAge(@PathVariable int id, @RequestBody Map<String, Integer> payload) {
        for(User u : users) {
            if(u.getId() == id) {
                u.setAge(payload.get("age"));
                return id + " User age patched!";
            }
        }
        return "User Not Found";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable int id) {
        users.removeIf(user -> user.getId() == id);
        return id + " User Deleted!";
    }
}
