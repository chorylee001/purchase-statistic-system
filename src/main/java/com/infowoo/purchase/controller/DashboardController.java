package com.infowoo.purchase.controller;

import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @RequestMapping("/")
    public String root(ModelMap modelMap) {

        UserInfo userInfo = UserUtil.getUser();
        modelMap.put("user",userInfo);
        return "index";
    }
    @RequestMapping("/index")
    public String index(ModelMap modelMap) {
        UserInfo userInfo = UserUtil.getUser();
        modelMap.put("user",userInfo);
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
