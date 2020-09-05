package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.StatisticData;

import java.util.List;

public interface IStatisticService {

    /**
     * 日统计
     * @param type
     * @param reportTime
     * @return
     */
    List<StatisticData> getDayStatistic(Integer type, String reportTime);
    /**
     * 月统计
     * @param type 上报类型
     * @return
     */
    List<StatisticData> getMonthStatistic(Integer type, String reportTime);
}
