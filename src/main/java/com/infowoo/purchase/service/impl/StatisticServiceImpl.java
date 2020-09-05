package com.infowoo.purchase.service.impl;

import com.infowoo.purchase.entity.StatisticData;
import com.infowoo.purchase.mapper.StatisticMapper;
import com.infowoo.purchase.service.IStatisticService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by David on 2020-09-05 11:56
 */
@Repository
public class StatisticServiceImpl implements IStatisticService {

    @Resource
    private StatisticMapper statisticMapper;

    @Override
    public List<StatisticData> getDayStatistic(Integer type, String reportTime) {
        return statisticMapper.getDayStatistic(type,reportTime);
    }

    @Override
    public List<StatisticData> getMonthStatistic(Integer type, String reportTime) {
        return statisticMapper.getMonthStatistic(type,reportTime);
    }
}
