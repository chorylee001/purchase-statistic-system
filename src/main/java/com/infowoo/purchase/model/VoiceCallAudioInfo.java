package com.infowoo.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 录音结构
 * Created by WuKong on 2018/12/27.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceCallAudioInfo {
    private Integer type;
    private String url;
    private Integer duration;
    @JsonFormat(pattern = "HH:mm:ss", timezone = "GMT+8")
    private Date callTime;
    private String text;
}
