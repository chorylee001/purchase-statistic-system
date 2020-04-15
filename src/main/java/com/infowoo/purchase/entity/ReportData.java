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

    private Integer type;

    private Integer orderCount;

    private Double orderAmount;

    private Integer parentCategory;

    private Integer childCategory;

    private Date reportTime;

    private String reportUser;

    private Date createTime;

    private Date updateTime;
}
