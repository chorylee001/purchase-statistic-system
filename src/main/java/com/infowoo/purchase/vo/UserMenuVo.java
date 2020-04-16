package com.infowoo.purchase.vo;

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
@NoArgsConstructor
@AllArgsConstructor
public class UserMenuVo {

    private String name;
    private String url;
    private List<String> ab;
}
