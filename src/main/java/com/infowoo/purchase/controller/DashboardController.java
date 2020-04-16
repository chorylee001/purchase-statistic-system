package com.infowoo.purchase.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }
    @RequestMapping("/index")
    public String index() {

        log.info("index...");
        return "index";
    }

    @RequestMapping("/dashboard/index")
    public String mainIndex() {
        return "dashboard/index";
    }


    @RequestMapping("/help")
    public String help() {
        return "help";
    }

}
