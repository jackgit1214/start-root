package com.example.client.demo;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UserController {

    @PostMapping("/getUser")
    @ResponseBody
    public List<UserPojo> getUser(@RequestParam("userName") String userName){

        List<UserPojo> userPojoList = getUserPojos();

        List<UserPojo> test = new ArrayList<UserPojo>();
        userPojoList.stream().filter(u->{
           return u.getUserName().equals(userName);
        }).forEach(u->{
            test.add(u);
        });

        return test;
    }

    @GetMapping("/getUser1")
    public List<UserPojo> getUser1(){

        return getUserPojos();

    }

    private List<UserPojo> getUserPojos() {
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
