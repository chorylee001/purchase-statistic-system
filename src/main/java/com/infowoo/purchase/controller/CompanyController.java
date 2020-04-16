package com.infowoo.purchase.controller;

import com.infowoo.purchase.entity.CompanyInfo;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.model.JsonResult;
import com.infowoo.purchase.service.ICompanyService;
import com.infowoo.purchase.utils.EncryptUtils;
import com.infowoo.purchase.vo.DTResponsePageVo;
import com.infowoo.purchase.vo.Pagination;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

@Slf4j
@RequestMapping("/company")
@Controller
public class CompanyController extends BaseController{

    @Autowired
    private ICompanyService companyService;


    @RequestMapping("/index")
    public String index(Model model) {
        log.info("company index");
        return "company/index";
    }


    @RequestMapping("/add")
    public @ResponseBody JsonResult add(@RequestParam("companyDesc") String companyDesc, @RequestParam("companyName")String companyName) {
        log.info("company add");
        UserInfo userInfo = getUserBaseInfo();
        CompanyInfo companyInfo = CompanyInfo.builder().companyName(companyName).companyDesc(companyDesc).createdAt(new Date()).updatedAt(new Date()).build();
        log.info("add username {} add companyInfo {}", userInfo.getUsername(),JSONObject.toJSONString(companyInfo));
//        companyService.addCompany(companyInfo);
        return JsonResult.buildSuccessResult("操作成功",companyInfo);
    }


    @RequestMapping("/v_list")
    public @ResponseBody  String list(Integer start, Integer length) {
        Pagination page = null;//companyService.getCompanyList(start,length);
        DTResponsePageVo responsePageVo = DTResponsePageVo.builder()
                .data(page.getList())
                .recordsTotal(page.getTotalCount())
                .status(Boolean.TRUE).build();
        return JSONObject.toJSONString(responsePageVo);
    }


    @RequestMapping("/v_user_list")
    public @ResponseBody  String userList(Integer start, Integer length,Long companyId) {
        Pagination page = null;//companyService.getUserList(start,length,companyId);
        DTResponsePageVo responsePageVo = DTResponsePageVo.builder()
                .data(page.getList())
                .recordsTotal(page.getTotalCount())
                .status(Boolean.TRUE).build();
        return JSONObject.toJSONString(responsePageVo);
    }

    @RequestMapping("/user_index")
    public String userIndex(Model model) {
        log.info("company user index");
//        model.addAttribute("companys", companyService.getAllCompany());
        return "company/user_index";
    }

    @RequestMapping("/add_user")
    public @ResponseBody JsonResult addUser(@RequestParam("username")String username,@RequestParam("userName")String userName,@RequestParam("password")String password,@RequestParam("companyId")Long companyId) {
        try {
            UserInfo oldUserInfo = companyService.findUserByUsername(username);
            if(Objects.nonNull(oldUserInfo)){
                return JsonResult.buildErrorStateResult("账号已经存在",null);
            }
            UserInfo userInfoVo = new UserInfo();
            userInfoVo.setUsername(username);
            userInfoVo.setCompanyId(companyId);
            userInfoVo.setRealName(userName);
            userInfoVo.setRoles(new HashSet<String>(){{
                add("guest");
            }});
            String pass = EncryptUtils.md5(EncryptUtils.md5(password,""), userInfoVo.getSalt());
            userInfoVo.setPassword(pass);
//            companyService.addUser(userInfoVo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return JsonResult.buildErrorStateResult("添加失败，联系相关人员");
        }
        return JsonResult.buildSuccessResult("添加成功");
    }

}
