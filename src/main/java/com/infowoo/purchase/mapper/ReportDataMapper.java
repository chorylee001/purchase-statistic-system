package com.infowoo.purchase.mapper;

import com.infowoo.purchase.entity.ReportData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportDataMapper {

    /**
     * 保存
     * @param record
     * @return
     */
    int insert(ReportData record);

    int update(ReportData record);


    /**
     * 根据ID查询
     * @param companyId
     * @return
     */
    ReportData findCompanyById(@Param("companyId") Long companyId);

    int insertSelective(ReportData record);

    /**
     * 查询所有企业
     * @param status
     * @return
     */
    List<ReportData> getAll(Integer status);
    List<ReportData> getList(@Param("startTime") String startTime,
                             @Param("endTime") String endTime,
                             @Param("userId") Long userId,
                             @Param("start") Integer start,
                             @Param("pageSize") Integer pageSize);
    int getCount(@Param("startTime") String startTime,
                 @Param("endTime") String endTime,
                 @Param("userId")Long userId);

    /**
     * 根据企业名称和状态模糊查询
     * @param companyName
     * @param status
     * @return
     */
    List<ReportData> getListByNameAndStatus(String companyName, Integer status);

    ReportData findById(@Param("id") Long id);

    List<ReportData> findByReportTimeAndUser(@Param("reportTime")String reportTime,@Param("createdUser")Integer createdUser,@Param("type") Integer type);
}