package com.vic.microserviceprovideruser.controller;

import com.vic.microserviceprovideruser.Cache;
import com.vic.microserviceprovideruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private Cache cache;

    @GetMapping(value = "/{id}", produces = "application/json")
    public User findById(@PathVariable Long id) {
        System.out.println(cache.getCacheByKey(id.toString()));
        return new User(id, "三大", "安安", 22, BigDecimal.ONE);
    }

    @GetMapping(value = "/user/list", produces = "application/json")
    public List<User> list() {
        List<User> result = new ArrayList<>();
        result.add(new User(1l, "三大", "安安", 22, BigDecimal.ONE));
        result.add(new User(2l, "三大", "安安", 22, BigDecimal.ONE));
        result.add(new User(3l, "三大", "安安", 22, BigDecimal.ONE));
        return result;
    }

    @GetMapping(value = "/get", produces = "application/json")
    public User get(User user) {
        return user;
    }

    @PostMapping(value = "/post", produces = "application/json")
    public User post(@RequestBody User user) {
        return user;
    }
}