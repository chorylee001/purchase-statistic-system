package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.StationInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StationInfoMapper {

    /**
     * 保存
     * @param stationInfo
     * @return
     */
    int insert(StationInfo stationInfo);

    int update(StationInfo stationInfo);
    /**
     * 根据ID查询
     * @param stationId
     * @return
     */
    StationInfo findStationById(@Param("stationId") Long stationId);

    int insertSelective(StationInfo record);

    /**
     * 查询所有企业
     * @param status
     * @return
     */
    List<StationInfo> getAll(Integer status);
    List<StationInfo> getList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);
    int getCount();

    /**
     * 根据企业名称和状态模糊查询
     * @param companyName
     * @param status
     * @return
     */
    List<StationInfo> getListByNameAndStatus(String companyName, Integer status);
}