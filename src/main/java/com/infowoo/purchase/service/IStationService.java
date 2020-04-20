package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.CompanyInfo;
import com.infowoo.purchase.entity.StationInfo;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.vo.Pagination;

import java.util.List;

/**
 * Created by David on 2019/4/28
 */
public interface IStationService {

    Pagination getUserList(Integer start, Integer pageSize, Long companyId);

    List<StationInfo> getAllStation();

    Pagination getStationList(Integer start, Integer pageSize);

    /**
     * 根据ID查询
     * @param stationId
     * @return
     */
    StationInfo findStationById(Long stationId);

    int saveOrUpdate(StationInfo stationInfo);

    void addUser(UserInfo userInfo);

    UserInfo findUserByUsername(String username);

    StationInfo findById(Long stationId);
}
