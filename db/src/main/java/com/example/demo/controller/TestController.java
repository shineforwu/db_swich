package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/Test")
public class TestController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/Show", method = RequestMethod.GET)
    public Object show() {
        return "OK";
    }
    @RequestMapping(value = "/All", method = RequestMethod.GET)
    public List<User> All() {
        //return null;
        return userService.getUsers();
    }
}
