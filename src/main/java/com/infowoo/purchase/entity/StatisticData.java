package com.infowoo.purchase.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticData {

    /**
     * 小类
     */
    private Integer childCategory;
    /**
     * 总订单数
     */
    private Integer totalCount;
    /**
     * 总金额
     */
    private Double totalAmount;
}
