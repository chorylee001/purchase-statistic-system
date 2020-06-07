package com.infowoo.purchase.service.impl;

import com.infowoo.purchase.entity.ReportData;
import com.infowoo.purchase.mapper.ReportDataMapper;
import com.infowoo.purchase.service.IReportService;
import com.infowoo.purchase.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Override
    public Pagination getReportList(String startTime,String endTime,Long userId,Integer start, Integer pageSize){

        start = start == null?0:start;
        pageSize = pageSize==null?10:pageSize;
        int totalCount = reportDataMapper.getCount(startTime,endTime,userId);
        Pagination p = new Pagination(start, pageSize, totalCount);
        p.setList(reportDataMapper.getList(startTime,endTime,userId,start,pageSize));
        return p;
    }

    @Override
    public ReportData findById(Long id){

        return reportDataMapper.findById(id);
    }

    @Override
    public List<ReportData> findByReportTimeAndUser(String reportTime,Integer createdUser,Integer type){

        return reportDataMapper.findByReportTimeAndUser(reportTime,createdUser,type);
    }

    @Override
    public int save(ReportData reportData){
        return reportDataMapper.insert(reportData);
    }

    @Override
    public int update(ReportData reportData){
        return reportDataMapper.update(reportData);
    }
}
