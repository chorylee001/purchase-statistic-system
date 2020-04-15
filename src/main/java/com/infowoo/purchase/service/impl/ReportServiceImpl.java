package com.infowoo.purchase.service.impl;

import com.infowoo.purchase.mapper.ReportDataMapper;
import com.infowoo.purchase.service.IReportService;
import com.infowoo.purchase.vo.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReportServiceImpl implements IReportService {

    @Autowired
    private ReportDataMapper reportDataMapper;

    @Override
    public Pagination getReportList(Integer start, Integer pageSize) {
        start = start == null?0:start;
        pageSize = pageSize==null?10:pageSize;
        int totalCount = reportDataMapper.getCount();
        Pagination p = new Pagination(start, pageSize, totalCount);
        p.setList(reportDataMapper.getList(start,pageSize));
        return p;
    }
}
