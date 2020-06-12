package com.vic.microserviceconsumermovie.controller;

import com.vic.microserviceconsumermovie.entity.User;
import com.vic.microserviceconsumermovie.feign.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/movies")
@RestController
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EchoService echoService;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        // 这里用到了RestTemplate的占位符能力
//        User user = this.restTemplate.getForObject(
//                "http://microservice-provider-user/{id}",
//                User.class,
//                id
//        );
        User user = echoService.findById(id);
        // ...电影微服务的业务...
        return user;
    }
}
