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
    private Long createdUser;
    private String reportTime;
    private Date createTime;
}
