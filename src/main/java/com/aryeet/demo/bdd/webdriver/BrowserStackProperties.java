package com.aryeet.demo.bdd.webdriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BrowserStackProperties {

    @Autowired
    private Environment environment;
    //To-Do via env
}