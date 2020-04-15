package com.infowoo.purchase.vo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDataStationReportXML {

    @JacksonXmlProperty(localName = "rptDate")
    private String rptDate;

    @JacksonXmlProperty(localName = "serviceStationReport")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ReportDataStationDataXML> serviceStationReport;
}
