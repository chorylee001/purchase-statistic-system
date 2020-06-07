package com.infowoo.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;

    private String username;

    private String realName;

    private String password;

    private String contactNumber;

    private Long stationId;

    private Integer roleId;

    private String salt;

    /**
     * 站点状态
     * 0 删除
     * 1 正常
     * 2 禁用
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;

    private StationInfo stationInfo;


    private Set<String> roles;

    private Set<String> permissions;

    private Long companyId;

    private CompanyInfo companyInfo;

}