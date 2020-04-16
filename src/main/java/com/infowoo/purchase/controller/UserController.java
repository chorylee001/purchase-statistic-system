package com.infowoo.purchase.controller;

import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.model.JsonResult;
import com.infowoo.purchase.service.IUserService;
import com.infowoo.purchase.utils.EncryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Slf4j
@Controller
public class UserController extends BaseController {
    @Resource
    private IUserService userService;

    @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "username",required = false) String username,
                        @RequestParam(value = "password",required = false) String password,
                        HttpServletRequest request,Model model) {

        log.info("login...");
        JsonResult result;
        if(StringUtils.isNoneBlank(username,password)){

            Subject subject = SecurityUtils.getSubject();
            try {
                UserInfo userInfo = userService.getUserBaseInfo(username);
                if(userInfo ==null){
                    result = JsonResult.buildErrorStateResult("用户不存在.");
                    model.addAttribute(result);
                    return "login";
                }
                String errorClassName = (String) request
                        .getAttribute("shiroLoginFailure");
                String authticationError = null;
                if (UnknownAccountException.class.getName().equals(errorClassName)) {
                    authticationError = "用户名/密码错误";
                } else if (IncorrectCredentialsException.class.getName().equals(
                        errorClassName)) {
                    authticationError = "用户名/密码错误";
                } else if (errorClassName != null) {
                    authticationError = "未知错误：" + errorClassName;
                }
                model.addAttribute("authticationError", authticationError);
                log.info("authticationInfo:"+authticationError);

                UsernamePasswordToken token = new UsernamePasswordToken(username, EncryptUtils.md5(password)+ userInfo.getSalt());
                subject.login(token);
                log.info("Principal:"+subject.getPrincipal().toString());
                Session session = subject.getSession();
                Serializable sessionId = session.getId();
                log.info("username {} login success, sessionId {}",username,sessionId);
                return "redirect:/index";
            } catch (IncorrectCredentialsException ice){
                log.info(ice.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        model.addAttribute(JsonResult.buildSuccessResult("success"));
        return "login";
    }




    @RequestMapping(value = "/logout")
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute(JsonResult.buildSuccessResult("success"));
        return "login";
    }
}
