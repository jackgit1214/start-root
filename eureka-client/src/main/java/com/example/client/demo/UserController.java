package com.example.client.demo;


import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @PostMapping("/getUser")
    @ResponseBody
    public List<UserPojo> getUser(@RequestParam("userName") String userName){

        System.out.println("---"+userName);

        List<UserPojo> userPojoList = new ArrayList<>();
        userPojoList.add(new UserPojo("tom","man",15));
        userPojoList.add(new UserPojo("jack","man",18));
        userPojoList.add(new UserPojo("ajan","wuman",17));

        return userPojoList;
    }

    @GetMapping("/getUser1")
    public List<UserPojo> getUser1(){

        List<UserPojo> userPojoList = new ArrayList<>();
        userPojoList.add(new UserPojo("tom","man",15));
        userPojoList.add(new UserPojo("jack","man",18));
        userPojoList.add(new UserPojo("ajan","wuman",17));

        return userPojoList;
    }

    @GetMapping("/getUserPojo")
    @ResponseBody
    public Object getTest(){
        return new UserPojo("tom","man",15);
    }
}
