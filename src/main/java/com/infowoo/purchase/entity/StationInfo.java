package com.infowoo.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationInfo {
    private Long id;
    private String stationName;
    private String stationCode;
    private Integer stationType;
    private Date createTime;

    private Date updateTime;
}
