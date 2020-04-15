package com.infowoo.purchase.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.infowoo.purchase.api.DataReportApi;
import com.infowoo.purchase.entity.VoiceRecordIntent;
import com.infowoo.purchase.model.JsonResult;
import com.infowoo.purchase.service.IReportService;
import com.infowoo.purchase.utils.DateUtil;
import com.infowoo.purchase.vo.*;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IReportService reportService;

    @Autowired
    private DataReportApi reportApi;

    @RequestMapping(value = "/index")
    public String index(Model model){
        return "report/index";
    }

    @RequestMapping(path = "/v_list")
    public @ResponseBody  String list(Integer start, Integer length) {
        Pagination page = reportService.getReportList(start,length);
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

    @RequestMapping(path = "/o_save")
    public @ResponseBody
    JsonResult save() throws JsonProcessingException {

        //构造xml格式数据并发送

        List<ReportDataStationReportXML> stationReportXMLS = new ArrayList<>();

        List<ReportDataStationDataXML> reportDataStationDataXMLS = new ArrayList<>();

        List<ReportDataCommodityXML> commodityXMLs = new ArrayList();
        ReportDataCommodityXML commodityXML = ReportDataCommodityXML
                .builder()
                .commId(12)
                .money(1750.00)
                .build();
        commodityXMLs.add(commodityXML);


        commodityXML = ReportDataCommodityXML
                .builder()
                .commId(6)
                .money(1750.00)
                .build();
        commodityXMLs.add(commodityXML);

        ReportDataStationDataXML stationDataXML = ReportDataStationDataXML
                .builder()
                .code(17354807190L)
                .name("达布达尔村")
                .countyType(2)
                .buyOrder(15)
                .saleOrder(3)
                .commodityXML(commodityXMLs)
                .build();
        reportDataStationDataXMLS.add(stationDataXML);

        ReportDataStationReportXML stationReportXML = ReportDataStationReportXML
                .builder()
                .rptDate(DateUtil.getFormat().format(new Date()))
                .serviceStationReport(reportDataStationDataXMLS)
                .build();

        stationReportXMLS.add(stationReportXML);

        ReportDataXML dataXML = ReportDataXML
                .builder()
                .serviceStation(stationReportXMLS)
                .build();
        dataXML.setServiceStation(stationReportXMLS);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setDefaultUseWrapper(false);
        //字段为null，自动忽略，不再序列化
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //XML标签名:使用骆驼命名的属性名，  
        xmlMapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
        //设置转换模式 
        xmlMapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        String result = xmlMapper.writeValueAsString(dataXML);
        System.out.println("序列化结果：" + result);

        Response response = reportApi.sendData(dataXML);
        Response.Body responseBody = response.body();
        log.info("TTS respose body:{}",responseBody.toString());

        if(response.status() != 200 || responseBody == null){
            return null;
        }
        return null;
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
