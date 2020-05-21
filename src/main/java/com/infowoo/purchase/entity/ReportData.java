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
public class ReportData {

    private Long id;

    /**
     * 类型：1代买，2代卖
     */
    private Integer type;

    /**
     * 代买总订单数
     */
    private Integer buyCount;
    /**
     * 销售总订单数
     */
    private Integer sellCount;

    /**
     * 金额
     */
    private Double amount;

    private Integer parentCategory;
    /**
     * 小类
     */
    private Integer childCategory;

    /**
     * 上报日期
     */
    private String reportTime;

    /**
     * 上报用户
     */
    private String reportUser;

    /**
     * 创建用户
     */
    private Long createdUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
