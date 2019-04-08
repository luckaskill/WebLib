package com.http.webservice.controller.tools;

import com.http.webservice.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class Forward {
    private final static String REDIRECT_TO_ADMIN_PAGE = "redirect:/admin_main";
    private final static String REDIRECT_TO_USER_MAIN = "redirect:/user_main";
    private final static String REDIRECT_TO_BLOCKED_PAGE = "redirect:/blocked_page";
    private final static String ADMIN_PAGE = "admin_page";
    private final static String USER_MAIN = "user_main";
    private final static String BLOCKED_PAGE = "user_main";


    public static String redirectByAccess(User user) {
        if (user.getAccessLevel() > 1) {
            return REDIRECT_TO_ADMIN_PAGE;
        } else if (user.getAccessLevel() == 1) {
            return REDIRECT_TO_USER_MAIN;
        } else {
            return REDIRECT_TO_BLOCKED_PAGE;
        }
    }
    public static String byAccess(User user) {
        if (user.getAccessLevel() > 1) {
            return ADMIN_PAGE;
        } else if (user.getAccessLevel() == 1) {
            return USER_MAIN;
        } else {
            return BLOCKED_PAGE;
        }
    }

    @GetMapping("/admin_main")
    public String goToAdminMain(){
        return ADMIN_PAGE;
    }

    @GetMapping("/user_main")
    public String goToSimpleUserMain(){
        return "simple_user_page";
    }

    @GetMapping("/blocked_page")
    public String goToBlockedPage(){
        return "blocked_page";
    }

}
