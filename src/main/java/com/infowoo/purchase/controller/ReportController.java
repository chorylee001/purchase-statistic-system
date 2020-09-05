package com.infowoo.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.infowoo.purchase.config.SyncServiceStationOperationRequest;
import com.infowoo.purchase.config.SyncServiceStationOperationResponse;
import com.infowoo.purchase.config.SyncServiceStationPortType;
import com.infowoo.purchase.config.SyncServiceStationPortTypeProxy;
import com.infowoo.purchase.entity.ReportData;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.entity.VoiceRecordIntent;
import com.infowoo.purchase.service.IReportService;
import com.infowoo.purchase.utils.DateUtil;
import com.infowoo.purchase.utils.UserUtil;
import com.infowoo.purchase.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.rmi.RemoteException;
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @RequestMapping(value = "/index")
    public String index(){
        return "report/index";
    }

    @RequestMapping(path = "/v_list")
    public @ResponseBody  String list(String reportTime,Integer start, Integer length) {

        String[] timeRange = DateUtil.getTimeRange(reportTime,"-",true);

        UserInfo userInfo = UserUtil.getUser();
        Pagination page;
        if(userInfo.getStationId() == 0){
            //管理员
            page = reportService.getReportList(timeRange[0],timeRange[1],null,start,length);
        }else{
            page = reportService.getReportList(timeRange[0],timeRange[1],userInfo.getId(),start,length);
        }

        DTResponsePageVo responsePageVo = DTResponsePageVo.builder()
                .data(page.getList())
                .recordsTotal(page.getTotalCount())
                .status(Boolean.TRUE).build();
        return JSONObject.toJSONString(responsePageVo);
    }

    @RequestMapping(value = "/v_add")
    public String add(Model model){

        return "report/add";
    }

    @RequestMapping("/v_detail")
    public String detail(String reportTime,Integer createdUser,Model model){

        model.addAttribute("reportBuyDatas", reportService.findByReportTimeAndUser(reportTime, createdUser,BusinessType.TYPE_BUY.getType()));
        model.addAttribute("reportSellDatas", reportService.findByReportTimeAndUser(reportTime, createdUser,BusinessType.TYPE_SELL.getType()));
        model.addAttribute("CategoryBuyMap",GoodsCategory.CategoryBuyMap);
        model.addAttribute("CategorySellMap",GoodsCategory.CategorySellMap);
        return "report/detail";
    }

    @RequestMapping("/v_edit")
    public String edit(String reportTime,Integer createdUser,Model model){



        model.addAttribute("reportBuyDatas", reportService.findByReportTimeAndUser(reportTime, createdUser,BusinessType.TYPE_BUY.getType()));
        model.addAttribute("reportSellDatas", reportService.findByReportTimeAndUser(reportTime, createdUser,BusinessType.TYPE_SELL.getType()));
        model.addAttribute("CategoryBuyMap",GoodsCategory.CategoryBuyMap);
        model.addAttribute("CategorySellMap",GoodsCategory.CategorySellMap);
        return "report/edit";
    }

    @RequestMapping(path = "/o_save")
    public String save(Integer createdUser,String oldReportTime,Integer buyCount, Integer sellCount, Integer[] buyChildCategorys, Double[] buyAmounts,
                    Integer[] sellChildCategorys, Double[] sellAmounts,
                    String reportTime, String reportUser, HttpServletRequest request) throws JsonProcessingException {

        UserInfo userInfo = UserUtil.getUser();

        buyCount = buyChildCategorys.length;
        sellCount = sellChildCategorys.length;

        Integer cate = 0;
        Double amount = 0.0;
        ReportData reportData = null;

        /**
         * 商务部上报数据格式对象
         */
        List<ReportDataCommodityXML> commodityXMLs = new ArrayList();
        ReportDataCommodityXML commodityXML;
        if(buyCount>0){
            for(int i=0;i<buyCount;i++){
                cate = buyChildCategorys[i];
                if(cate == -1){
                    continue;
                }
                amount = buyAmounts[i];

                reportData = ReportData.builder()
                        .type(BusinessType.TYPE_BUY.getType())
                        .childCategory(cate)
                        .amount(amount)
                        .reportUser(reportUser)
                        .reportTime(reportTime)
                        .build();
                if(Objects.isNull(createdUser)){
                    reportData.setCreatedUser(userInfo.getId());
                    reportData.setCreateTime(new Date());
                    reportData.setUpdateTime(new Date());
                    reportService.save(reportData);
                }else{
                    reportData.setCreatedUser(createdUser.longValue());
                    reportData.setUpdateTime(new Date());
                    if(oldReportTime.indexOf(",")!=-1){
                        oldReportTime = oldReportTime.split(",")[0];
                    }
                    reportData.setOldReportTime(oldReportTime);
                    reportService.update(reportData);

                }

                commodityXML = ReportDataCommodityXML
                        .builder()
                        .commId(cate)
                        .money(amount)
                        .build();
                commodityXMLs.add(commodityXML);
            }
        }

        if(sellCount>0){
            for(int i=0;i<sellCount;i++){
                cate = sellChildCategorys[i];
                if(cate == -1){
                    continue;
                }
                amount = sellAmounts[i];

                reportData = ReportData.builder()
                        .type(BusinessType.TYPE_SELL.getType())
                        .childCategory(cate)
                        .amount(amount)
                        .reportUser(reportUser)
                        .reportTime(reportTime)
                        .build();

                if(Objects.isNull(createdUser)){
                    reportData.setCreatedUser(userInfo.getId());
                    reportData.setCreateTime(new Date());
                    reportData.setUpdateTime(new Date());
                    reportService.save(reportData);
                }else{
                    reportData.setCreatedUser(createdUser.longValue());
                    reportData.setUpdateTime(new Date());

                    if(oldReportTime.indexOf(",")!=-1){
                        oldReportTime = oldReportTime.split(",")[0];
                    }
                    reportData.setOldReportTime(oldReportTime);
                    reportService.update(reportData);

                }

                commodityXML = ReportDataCommodityXML
                        .builder()
                        .commId(cate)
                        .money(amount)
                        .build();
                commodityXMLs.add(commodityXML);
            }
        }

        //构造xml格式数据并发送
        List<ReportDataStationDataXML> reportDataStationDataXMLS = new ArrayList<>();
        ReportDataStationDataXML stationDataXML = ReportDataStationDataXML
                .builder()
                .code(userInfo.getStationInfo().getId())
                .name(userInfo.getStationInfo().getStationName())
                .countyType(userInfo.getStationInfo().getStationType())
                .buyOrder(buyCount)
                .saleOrder(sellCount)
                .commodityXML(commodityXMLs)
                .build();
        reportDataStationDataXMLS.add(stationDataXML);

        ReportDataStationReportXML stationReportXML = ReportDataStationReportXML
                .builder()
                .userId("6531310030")
                .rptDate(DateUtil.getFormat().format(new Date()))
                .serviceStationReport(reportDataStationDataXMLS)
                .build();

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，  
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式 
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        String result = xmlMapper.writeValueAsString(stationReportXML);

        /*try {
            dataSend(result);
        } catch (RemoteException e) {
            log.info("向商务部站点发送数据异常.",e.getMessage());
        }*/
        return index();
    }

    private String dataSend(String xmlBody) throws RemoteException {
        SyncServiceStationPortTypeProxy proxy = new SyncServiceStationPortTypeProxy();
        proxy.setEndpoint("http://211.88.20.132:8040/services/syncServiceStation?wsdl");
        SyncServiceStationPortType portType = proxy.getSyncServiceStationPortType();
        String in = "<?xml version='1.0' encoding='UTF-8'?>"+xmlBody;
        SyncServiceStationOperationResponse response = portType
                .syncServiceStationOperation(new SyncServiceStationOperationRequest(in));

        log.info("syncServiceStation response:",response.getOut());
        return response.getOut();
    }

    @RequestMapping(path = "/update")
    public @ResponseBody
    VoiceRecordIntent saveAll(@RequestBody VoiceRecordIntent intention){

        return intention;
    }

    @RequestMapping(path = "/deleteById")
    public @ResponseBody
    Boolean deleteById(@RequestParam String id){

        return Boolean.TRUE;
    }
}
