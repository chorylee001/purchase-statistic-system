package com.infowoo.purchase.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataCommodityXML {

    //isAttribute = true,
    @JacksonXmlProperty(localName = "commId")
    private Integer commId;
    @JacksonXmlProperty(localName = "money")
    private Double money;
}
