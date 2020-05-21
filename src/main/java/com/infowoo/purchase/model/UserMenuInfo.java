package com.infowoo.purchase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * Created by WuKong on 2018/12/20.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserMenuInfo {

    private String menuName;
    private String menuUrl;
    private List<UserButtonInfo> menuOps;
    private Set<String> menuRoles;
    private Set<String> menuPermissions;
}
