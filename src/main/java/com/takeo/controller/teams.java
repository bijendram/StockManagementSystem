package com.takeo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class teams {
    @RequestMapping("/teams")
    public String contactPage() {

        return "teams.html";
    }

}
