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
public class CompanyInfo {
    private Long id;

    private String companyName;

    private Date expiredTime;

    private Date createdAt;

    private Date updatedAt;

    private String companyDesc;

    /**
     * 企业状态
     * 0删除 1正常 2限制 3禁用
     */
    private Integer status;

}