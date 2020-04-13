package com.lin.controller;

import com.lin.service.test.TestRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api("测试类")
public class TestController {

    @GetMapping("/hello")
    @ApiOperation("hello")
    public String hello(){
        String a  = "aadsa";
        String b = "aasfss1" + a;
        return b;
    }

    @RequestMapping(value = "/request", method = RequestMethod.POST)
    @ApiOperation("request")
    public String request(TestRequest test){
        return test.getParam1();
    }

}
