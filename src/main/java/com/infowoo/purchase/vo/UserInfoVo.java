package com.infowoo.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by WuKong on 2018/12/19.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {

    public Long userId;

    private String userName;

    private String name;

    private Long companyId;

    private String companyName;

    private String token;

    private Date expiredTime;

}
