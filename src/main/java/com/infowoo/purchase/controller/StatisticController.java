package com.infowoo.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.infowoo.purchase.entity.StatisticData;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.service.IStatisticService;
import com.infowoo.purchase.utils.DateUtil;
import com.infowoo.purchase.utils.UserUtil;
import com.infowoo.purchase.vo.BusinessType;
import com.infowoo.purchase.vo.DTResponsePageVo;
import com.infowoo.purchase.vo.GoodsCategory;
import com.infowoo.purchase.vo.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2020-06-20 18:22
 */
@Slf4j
@RequestMapping("/statistic")
@Controller
public class StatisticController {

    @Autowired
    private IStatisticService statisticService;

    @RequestMapping("/index")
    public String dayStat(String type,String reportTime,Model model) {

        model.addAttribute("CategoryBuyMap", GoodsCategory.CategoryBuyMap);
        model.addAttribute("CategorySellMap",GoodsCategory.CategorySellMap);

        String page = "statistic/day";
        if("month".equals(type)){
            page = "statistic/month";
            reportTime = StringUtils.isEmpty(reportTime)?DateUtil.getTodayYM():reportTime;
            model.addAttribute("buyDatas",statisticService.getMonthStatistic(BusinessType.TYPE_BUY.getType(),reportTime));
            model.addAttribute("sellDatas",statisticService.getMonthStatistic(BusinessType.TYPE_SELL.getType(),reportTime));
        }else{
            reportTime = StringUtils.isEmpty(reportTime)?DateUtil.getTodayYMD():reportTime;
            model.addAttribute("buyDatas",statisticService.getDayStatistic(BusinessType.TYPE_BUY.getType(),reportTime));
            model.addAttribute("sellDatas",statisticService.getDayStatistic(BusinessType.TYPE_SELL.getType(),reportTime));
        }
        model.addAttribute("reportTime",reportTime);
        return page;
    }

    @RequestMapping(path = "/get_list")
    public @ResponseBody
    Map<String, List<StatisticData>> get_list(String type, String reportTime) {

        if("month".equals(type)){
            reportTime = StringUtils.isEmpty(reportTime)?DateUtil.getTodayYM():reportTime;
        }else{
            reportTime = StringUtils.isEmpty(reportTime)?DateUtil.getTodayYMD():reportTime;
        }
        Map<String, List<StatisticData>> dataMap = new HashMap<>();
        ;
        dataMap.put("buyData",statisticService.getDayStatistic(BusinessType.TYPE_BUY.getType(),reportTime));
        dataMap.put("sellData",statisticService.getDayStatistic(BusinessType.TYPE_SELL.getType(),reportTime));
        return dataMap;
    }
}
