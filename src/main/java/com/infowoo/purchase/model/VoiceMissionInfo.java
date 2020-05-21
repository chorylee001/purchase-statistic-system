package com.infowoo.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by WuKong on 2019/1/7.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceMissionInfo {
    private String number;
    private String companyName;
    private String missionDesc;
    private String missionId;
    private String userTag;
    private String userTagDesc;
    private Integer taskState;
    private Integer callState;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date callTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationTime;
    private Long numberInfoId;

}
