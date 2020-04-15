package com.infowoo.purchase.api;

import com.infowoo.purchase.config.FeignConfiguration;
import com.infowoo.purchase.model.JsonResult;
import com.infowoo.purchase.vo.ReportDataXML;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name= "data-report",url = "http://211.88.20.132:8040/services",configuration = FeignConfiguration.class)
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public interface DataReportApi {

    /*@GetMapping(value = "/getUserList",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    List<String> getOrderByUserList();
*/

    @RequestMapping(value = "/syncServiceStation?wsdl",
            method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_XML_VALUE },
            produces = MediaType.APPLICATION_JSON_VALUE)
    Response sendData(@RequestBody ReportDataXML reportDataXML);

}
