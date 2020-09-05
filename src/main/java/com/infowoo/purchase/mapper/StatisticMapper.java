package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.StatisticData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StatisticMapper {

    /**
     * 日统计
     * @param type
     * @param reportTime
     * @return
     */
    List<StatisticData> getDayStatistic(@Param("type")Integer type, @Param("reportTime")String reportTime);
    /**
     * 月统计
     * @param type 上报类型
     * @return
     */
    List<StatisticData> getMonthStatistic(@Param("type")Integer type, @Param("reportTime")String reportTime);
}