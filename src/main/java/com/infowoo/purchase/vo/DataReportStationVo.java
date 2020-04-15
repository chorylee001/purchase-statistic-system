package com.infowoo.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataReportStationVo {

    private Long id;
    private String stationName;
    private Integer stationType;
    private Date reportTime;
    private Date createTime;
}
