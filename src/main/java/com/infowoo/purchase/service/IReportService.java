package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.ReportData;
import com.infowoo.purchase.vo.Pagination;

import java.util.List;

public interface IReportService {



    Pagination getReportList(String startTime,String endTime,Long userId,Integer start, Integer pageSize);

    int save(ReportData reportData);

    int update(ReportData reportData);

    ReportData findById(Long id);

    List<ReportData> findByReportTimeAndUser(String reportTime,Integer createdUser,Integer type);

}
