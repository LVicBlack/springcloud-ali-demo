package com.vic.microserviceprovideruser.entity;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class User {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;

    public User() {
    }

    public User(Long id, String username, String name, Integer age, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.age = age;
        this.balance = balance;
    }
}
