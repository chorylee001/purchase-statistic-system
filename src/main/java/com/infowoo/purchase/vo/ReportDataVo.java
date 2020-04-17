package com.infowoo.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportDataVo {

    private Long id;

    /**
     * 类型：1代买，2代卖
     */
    private Integer type;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 大类
     */
    private Integer parentCategory;

    /**
     * 小类
     */
    private Integer childCategory;

    /**
     * 上报日期
     */
    private Date reportTime;

    /**
     * 上报用户
     */
    private String reportUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
