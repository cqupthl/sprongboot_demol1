package com.asion.springboot_demol1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginContoller {

    @PostMapping(value="/user/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("passWord") String passWord,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(userName)&&"12345".equals(passWord)){
            session.setAttribute("loginUername",userName);
            return "redirect:/main.html";
        }else{
            map.put("msg","用户名或密码错误");
            return "login";
        }
    }
}
