package com.asion.springboot_demol1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloContoller {

 /*   @RequestMapping({"/","index.html"})
    public String index(){
        return "index";
    }*/

    @RequestMapping("/suceess")
    public String getHello( Map<String,Object> map){
        map.put("hello","你好");
        return "suceess";
    }
}
