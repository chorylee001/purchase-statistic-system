package com.infowoo.purchase.utils;

import java.util.Objects;

/**
 * Created by David on 2019/7/11
 */
public class RecordUtil {

    public static String getHitRuleByType(Integer hitRuleType){
        switch (hitRuleType){
            case 1:
                return "关键字";
            case 2:
                return "有识别结果但未匹配或无识别结果";
            case 3:
                return "其他";
            default:
                return null;
        }
    }

    public static String getActionType(Integer actionType){
        switch (actionType){
            case 1:
                return "结束";
            case 2:
                return "播放";
            case 3:
                return "播放结束";
            case 4:
                return "等待";
            case 5:
                return "播放上一语句";
            default:
                return null;
        }
    }

    public static String getTrueOrFalse(Integer useCompose){
        if(Objects.isNull(useCompose)){
            return null;
        }
        switch (useCompose){
            case 1:
                return "是";
            case 0:
                return "否";
            default:
                return null;
        }
    }
}
