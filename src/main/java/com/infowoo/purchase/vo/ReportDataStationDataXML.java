package com.infowoo.purchase.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataStationDataXML {

    @JacksonXmlProperty(localName = "code")
    private Long code;
    @JacksonXmlProperty(localName = "name")
    private String name;
    @JacksonXmlProperty(localName = "countyType")
    private Integer countyType;
    @JacksonXmlProperty(localName = "buyOrder")
    private Integer buyOrder;
    @JacksonXmlProperty(localName = "saleOrder")
    private Integer saleOrder;

    @JacksonXmlProperty(localName = "serviceStationCommodity")
    private List<ReportDataCommodityXML> commodityXML;
}
