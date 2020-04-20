package com.infowoo.purchase.controller;

import com.infowoo.purchase.entity.CompanyInfo;
import com.infowoo.purchase.entity.StationInfo;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.model.JsonResult;
import com.infowoo.purchase.service.IStationService;
import com.infowoo.purchase.service.IUserService;
import com.infowoo.purchase.utils.CommonUtil;
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
@RequestMapping("/station")
@Controller
public class StationController extends BaseController{

    @Autowired
    private IStationService stationService;
    @Autowired
    private IUserService userService;


    @RequestMapping("/index")
    public String index(Model model) {
        return "station/index";
    }


    @RequestMapping("/add")
    public @ResponseBody JsonResult add(StationInfo stationInfo,UserInfo userInfo,Integer stationId,Integer userId,Integer updatePass) throws Exception {



        StationInfo stationInf = StationInfo
                .builder()
                .stationName(stationInfo.getStationName())
                .stationCode(stationInfo.getStationCode())
                .stationType(stationInfo.getStationType())
                .build();
        if(Objects.nonNull(stationId)){
            stationInf.setId(stationId.longValue());
            stationInf.setUpdateTime(new Date());
        }else{
            stationInf.setCreateTime(new Date());
        }
        stationService.saveOrUpdate(stationInf);

        UserInfo userInf = UserInfo.builder()
                .stationId(stationInf.getId())
                .username(userInfo.getUsername())
                .realName(userInfo.getRealName())
                .contactNumber(userInfo.getContactNumber())
                .status(1)
                .build();
        if(Objects.nonNull(userId)){
            userInf.setId(userId.longValue());
            userInf.setUpdateTime(new Date());
        }else{
            userInf.setCreateTime(new Date());
        }
        if(Objects.isNull(userId)||Objects.nonNull(updatePass)){
            userInf.setPassword(EncryptUtils.md5(EncryptUtils.md5(userInfo.getPassword(),""), userInf.getSalt()));
        }
        userService.saveOrUpdate(userInf);
        return JsonResult.buildSuccessResult("操作成功",stationInf);
    }


    @RequestMapping("/v_list")
    public @ResponseBody  String list(Integer start, Integer length) {
        Pagination page = stationService.getStationList(start,length);
        DTResponsePageVo responsePageVo = DTResponsePageVo.builder()
                .data(page.getList())
                .recordsTotal(page.getTotalCount())
                .status(Boolean.TRUE).build();
        return JSONObject.toJSONString(responsePageVo);
    }

    @RequestMapping("/v_edit")
    public @ResponseBody JsonResult edit(Long id){

        return JsonResult.buildSuccessResult("success.",stationService.findStationById(id));
    }


    @RequestMapping("/v_user_list")
    public @ResponseBody  String userList(Integer start, Integer length,Long companyId) {
        Pagination page = null;//stationService.getUserList(start,length,companyId);
        DTResponsePageVo responsePageVo = DTResponsePageVo.builder()
                .data(page.getList())
                .recordsTotal(page.getTotalCount())
                .status(Boolean.TRUE).build();
        return JSONObject.toJSONString(responsePageVo);
    }

    @RequestMapping("/user_index")
    public String userIndex(Model model) {
        log.info("station user index");
//        model.addAttribute("companys", stationService.getAllCompany());
        return "company/user_index";
    }

    @RequestMapping("/add_user")
    public @ResponseBody JsonResult addUser(@RequestParam("username")String username,@RequestParam("userName")String userName,@RequestParam("password")String password,@RequestParam("companyId")Long companyId) {
        try {
            UserInfo oldUserInfo = stationService.findUserByUsername(username);
            if(Objects.nonNull(oldUserInfo)){
                return JsonResult.buildErrorStateResult("账号已经存在",null);
            }
            UserInfo userInfoVo = new UserInfo();
            userInfoVo.setUsername(username);
            userInfoVo.setStationId(companyId);
            userInfoVo.setRealName(userName);
            userInfoVo.setRoles(new HashSet<String>(){{
                add("guest");
            }});
            String pass = EncryptUtils.md5(EncryptUtils.md5(password,""), userInfoVo.getSalt());
            userInfoVo.setPassword(pass);
//            stationService.addUser(userInfoVo);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return JsonResult.buildErrorStateResult("添加失败，联系相关人员");
        }
        return JsonResult.buildSuccessResult("添加成功");
    }

}
