package com.infowoo.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by WuKong on 2018/12/22.
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MissionPhoneInfo {
    
    private String phone;
    private Map<String,String> variableMap;
}
