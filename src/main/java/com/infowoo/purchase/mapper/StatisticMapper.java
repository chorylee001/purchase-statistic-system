package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.StationInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticMapper {

    /**
     * 月统计
     * @param type 上报类型
     * @return
     */
    List<StationInfo> getMonthStatistic(Integer type);
}