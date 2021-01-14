package com.example.consumer.demo;

//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.consumer.config.GitAutoRefreshConfig;
import com.example.consumer.config.GitConfig;

@RestController
//@RefreshScope
public class UserController {

    private final UserService userService;

    public UserController(UserService userService, GitConfig gitConfig, GitAutoRefreshConfig gitAutoRefreshConfig) {
        this.userService = userService;
        this.gitConfig = gitConfig;
        this.gitAutoRefreshConfig = gitAutoRefreshConfig;
    }

    @RequestMapping("getUser")
    @ResponseBody
    public List<UserPojo> getUser(){
        return userService.getUser();
    }

    private final GitConfig gitConfig;

    private final GitAutoRefreshConfig gitAutoRefreshConfig;

    @GetMapping(value = "show")
    public Object show(){
        return gitConfig;
    }

    @GetMapping(value = "autoShow")
    public Object autoShow(){
        return gitAutoRefreshConfig;
    }
}
