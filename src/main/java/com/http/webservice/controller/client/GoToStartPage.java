package com.http.webservice.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/start", "/"})
public class GoToStartPage {
    @GetMapping
    public String toStartPage() {
        return "start_page";
    }
}
