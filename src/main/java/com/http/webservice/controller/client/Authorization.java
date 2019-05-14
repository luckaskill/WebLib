package com.http.webservice.controller.client;

import com.http.webservice.controller.tools.Forward;
import com.http.webservice.entity.User;
import com.http.webservice.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class Authorization {
    private final ClientServiceImpl service;

    @PostMapping("/login")
    public String logIn(@RequestParam String login, @RequestParam String password, Model model, HttpSession session) {
        User user = service.authorization(login, password);

        if (user != null) {
            session.setAttribute("user", user);
            return Forward.redirectByAccess(user);
        } else {
            model.addAttribute("message", "Incorrect login or password");
            return "start_page";
        }
    }
}
