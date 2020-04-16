package com.infowoo.purchase.vo;

import lombok.*;

import java.util.List;

/**
 * Created by David on 2019/4/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DTResponsePageVo {

    /**
     * 绘制计数器
     * 确保Ajax从服务器返回的是对应的请求
     */
    private Integer draw;
    /**
     * 总共记录数
     */
    private Integer recordsTotal;
    /**
     * 过滤后的记录数
     */
    private Integer recordsFiltered;
    /**
     * 对象数组
     */
    private List data;
    /**
     * 错误提示信息
     */
    private String error;

    /**
     * 成功状态
     */
    private Boolean status;
}
