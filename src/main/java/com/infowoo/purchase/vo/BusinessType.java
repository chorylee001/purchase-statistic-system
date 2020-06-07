package com.infowoo.purchase.vo;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 2020-05-24 19:20
 */
@Getter
public enum BusinessType {




    TYPE_BUY(1, "代买"),
    TYPE_SELL(2, "销售");
    public Integer type;
    public String desc;
    BusinessType(Integer type, String desc){
        this.type = type;
        this.desc = desc;
    }
}
