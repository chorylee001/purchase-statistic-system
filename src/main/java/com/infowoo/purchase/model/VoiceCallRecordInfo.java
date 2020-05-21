package com.infowoo.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 通话记录列表
 * Created by WuKong on 2018/12/26.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceCallRecordInfo {
    private Long recordId;
    private String number;
    private String taskUuid;
    private String callid;
    private String missionId;
    private String callRemark;
    private String userTag;
    private String userTagDesc;
    private Integer callDuration;
    /**
     * 拨打时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date callTime;
    /**
     * 接通时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date callInTime;
    /**
     * 挂断时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date hangUpTime;
    private String documentDesc;
    private String documentId;
    private String sipAccount;

    /**
     * 对话录音
     */
    List<VoiceCallAudioInfo>  callAudioInfos;
}
