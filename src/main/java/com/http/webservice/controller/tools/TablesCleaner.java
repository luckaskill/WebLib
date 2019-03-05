package com.http.webservice.controller.tools;

import javax.servlet.http.HttpSession;

public class TablesCleaner {
    private static final String[] elementsNames = {"books", "rentBooks", "users"};

    public static void cleanAllExcept(HttpSession session, String exceptElement) {
        for (String elementName : elementsNames) {
            if(elementName.equals(exceptElement)){
                continue;
            }
            session.setAttribute(elementName, null);
        }
    }
}
