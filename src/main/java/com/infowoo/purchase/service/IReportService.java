package com.infowoo.purchase.service;

import com.infowoo.purchase.entity.ReportData;
import com.infowoo.purchase.vo.Pagination;

public interface IReportService {



    Pagination getReportList(String startTime,String endTime,Long userId,Integer start, Integer pageSize);

    int save(ReportData reportData);
}
