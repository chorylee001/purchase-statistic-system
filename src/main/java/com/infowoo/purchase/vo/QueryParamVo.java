package com.infowoo.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by David on 2019/4/26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryParamVo {

    /**
     * 绘制计数器
     * 确保Ajax从服务器返回的是对应的
     */
    private Integer draw;
    /**
     * 第一条数据的起始位置
     * 0为第一条数据
     */
    private Integer start;
    /**
     * 每页显示的条数
     * -1时为全部数据
     */
    private Integer length;
    /**
     * 全局搜索条件
     */
    private String search;
    /**
     * 排序字段 or 数组
     */
    private Integer order;

    /**
     * 列排序方式
     * desc降序 asc升序
     */
//    private String orderType;


    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
