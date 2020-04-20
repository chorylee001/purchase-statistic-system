package com.infowoo.purchase.service.impl;

import com.infowoo.purchase.entity.StationInfo;
import com.infowoo.purchase.entity.UserInfo;
import com.infowoo.purchase.entity.UserRoleInfo;
import com.infowoo.purchase.mapper.StationInfoMapper;
import com.infowoo.purchase.mapper.UserBaseInfoMapper;
import com.infowoo.purchase.mapper.UserRoleInfoMapper;
import com.infowoo.purchase.service.IStationService;
import com.infowoo.purchase.vo.Pagination;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by David on 2019/4/28
 */
@Repository
public class StationServiceImpl implements IStationService {

    @Resource
    private UserBaseInfoMapper userBaseInfoMapper;

    @Resource
    private UserRoleInfoMapper userRoleInfoMapper;
    @Resource
    private StationInfoMapper stationInfoMapper;

    @Override
    public List<StationInfo> getAllStation() {
        return stationInfoMapper.getAll(null);
    }

    @Override
    public int saveOrUpdate(StationInfo stationInfo){
        if(Objects.isNull(stationInfo.getId())){
            return stationInfoMapper.insert(stationInfo);
        }else{
            return stationInfoMapper.update(stationInfo);
        }
    }

    @Override
    public Pagination getUserList(Integer start, Integer pageSize, Long StationId){
        start = start == null?0:start;
        pageSize = pageSize==null?10:pageSize;
        int totalCount = userBaseInfoMapper.getCount(StationId);
        Pagination p = new Pagination(start, pageSize, totalCount);
        p.setList(userBaseInfoMapper.getList(start,pageSize,StationId));
        return p;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUser(UserInfo userInfo){
        userBaseInfoMapper.insert(userInfo);
        Date now = new Date();
        if(!CollectionUtils.isEmpty(userInfo.getRoles())){
            userInfo.getRoles().forEach(role -> {
                UserRoleInfo userRoleInfo = UserRoleInfo.builder()
                        .role(role)
                        .userId(userInfo.getId())
                        .createdAt(now)
                        .updatedAt(now).build();
                userRoleInfoMapper.insert(userRoleInfo);
            });
        }
    }
    @Override
    public Pagination getStationList(Integer start, Integer pageSize) {
        start = start == null?0:start;
        pageSize = pageSize==null?10:pageSize;
        int totalCount = stationInfoMapper.getCount();
        Pagination p = new Pagination(start, pageSize, totalCount);
        p.setList(stationInfoMapper.getList(start,pageSize));
        return p;
    }

    @Override
    public StationInfo findStationById(Long StationId){

        return stationInfoMapper.findStationById(StationId);
    }
    @Override
    public UserInfo findUserByUsername(String username){
        return userBaseInfoMapper.findUserByUsername(username);
    }

    @Override
    public StationInfo findById(Long stationId){
        return stationInfoMapper.findStationById(stationId);
    }
}
