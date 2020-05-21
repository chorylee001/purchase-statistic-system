package com.infowoo.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by WuKong on 2018/12/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserButtonInfo {

    private String buttonName;
    private List<String> buttonRoles;
    private List<String> buttonPermissions;
}
