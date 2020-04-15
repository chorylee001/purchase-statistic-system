package com.infowoo.purchase.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JacksonXmlRootElement(localName = "items")
public class ReportDataXML {

    @JacksonXmlProperty(localName = "serviceStation")
    @JacksonXmlElementWrapper(useWrapping = false,localName = "serviceStation")
    private List<ReportDataStationReportXML> serviceStation;



}
