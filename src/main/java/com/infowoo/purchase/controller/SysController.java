package com.infowoo.purchase.controller;

import com.infowoo.purchase.model.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David on 2019/5/6
 */
@Slf4j
@RequestMapping(path = "/sys")
@RestController()
public class SysController {

    @RequiresGuest
    @RequestMapping(path = "/sys_check")
    public JsonResult systemCheck(){
        return JsonResult.buildSuccessResult("系统运行正常",null);
    }
}
