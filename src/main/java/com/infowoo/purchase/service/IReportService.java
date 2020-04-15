package com.infowoo.purchase.service;

import com.infowoo.purchase.vo.Pagination;

public interface IReportService {


    Pagination getReportList(Integer start, Integer pageSize);
}
