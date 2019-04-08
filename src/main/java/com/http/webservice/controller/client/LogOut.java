package com.http.webservice.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogOut {

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/start";
    }
}
