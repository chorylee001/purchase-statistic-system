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

    private String salt;

    private Date createTime;

    private Date updateTime;

    private StationInfo stationInfo;



    private Integer state;

    private Long companyId;

    private Set<String> roles;

    private Set<String> permissions;

    private CompanyInfo companyInfo;

}