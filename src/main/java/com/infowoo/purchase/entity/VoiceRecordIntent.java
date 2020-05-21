package com.infowoo.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoiceRecordIntent {

    private String id;
    /**
     * 意图ID
     */
    private String intentId;
    /**
     * 意图名称
     */
    private String name;

    /**
     * 意图权值 （用于排序）
     */
    private BigDecimal weight;

    /**
     * 关键词集
     * @param args
     */
    private List<String> keywords;

    /**
     * 流转话术ID
     */
    private String flowId;

    /**
     * 企业ID
     */
    private Long companyId;

    private Date createdAt;
    private Date updatedAt;

    /**
     * 状态
     * 1 正常；0 删除
     */
    private Integer status;
    /**
     * 父意图
     */
    private VoiceRecordIntent parentIntention;
    /**
     * 子意图
     */
    private List<VoiceRecordIntent> childIntentions;

}
