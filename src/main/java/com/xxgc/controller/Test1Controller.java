package com.xxgc.controller;

import com.xxgc.entity.User;
import com.xxgc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test1Controller {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/test")
    public String test(Model model){
        return "testasdfasddfasfasd";
    }

    @GetMapping(value = "/test1")
    public List<User> test1(){
        System.out.println(userMapper.findAll());
        return userMapper.findAll();
    }
}
