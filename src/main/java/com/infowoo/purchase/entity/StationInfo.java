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
    /**
     * 站点类型
     * 1 乡镇级服务站
     * 2 村级服务站
     */
    private Integer stationType;
    private Date createTime;
    private Date updateTime;

    private UserInfo userInfo;
}
