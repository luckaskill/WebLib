package com.http.webservice.entity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringApplicationContext {
    private static ApplicationContext context;

    private SpringApplicationContext() {
    }

    public static ApplicationContext getContext() {
        context = new AnnotationConfigApplicationContext("com.http.webservice");
        return context;
    }
}
