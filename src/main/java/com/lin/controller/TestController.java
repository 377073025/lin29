package com.lin.controller;

import com.lin.mapper.entity.User;
import com.lin.service.test.TestRequest;
import com.lin.service.test.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api("测试类")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(){
        String a  = "aadsa";
        String b = "aasfss1" + a;
        return b;
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    @ApiOperation("request")
    public User request(TestRequest test){
        return testService.getUser(test);
    }

}
