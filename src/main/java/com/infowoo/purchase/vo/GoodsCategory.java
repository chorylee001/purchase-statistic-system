package com.infowoo.purchase.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David on 2020-05-24 19:20
 */
public enum GoodsCategory {




    CATEGORY_1(4, "1粮油类"),
    CATEGORY_2(5, "2生鲜食品类");
    public static Map<Integer,String> CategoryBuyMap = new HashMap<Integer,String>(){{
        put(4,"1粮油类");
        put(5,"2生鲜食品类");
        put(6,"3干货类");
        put(8,"1休闲食品类");
        put(9,"2烟酒类");
        put(10,"3服装、鞋帽、针纺织品类");
        put(11,"4化妆品类");
        put(12,"5金银珠宝类");
        put(13,"6日用品类");
        put(14,"7家用电器和音像器材类");
        put(15,"8中西药品类");
        put(16,"9文化办公用品类");
        put(17,"10家具类");
        put(18,"11通讯器材类");
        put(19,"12建筑及装潢材料类");
        put(20,"1农用生产资料类");
        put(21,"2化工产品类");
        put(22,"3机电类");
        put(23,"4木材类");
    }} ;
    public static Map<Integer,String> CategorySellMap = new HashMap<Integer,String>(){{
        put(27,"1粮油类");
        put(28,"2生鲜食品类");
        put(29,"3干货类");
        put(30,"1休闲食品类");
        put(32,"2烟酒类");
        put(33,"3服装、鞋帽、针纺织品类");
        put(34,"4化妆品类");
        put(35,"5金银珠宝类");
        put(36,"6日用品类");
        put(37,"7家用电器和音像器材类");
        put(38,"8中西药品类");
        put(39,"9文化办公用品类");
        put(40,"10家具类");
        put(41,"11通讯器材类");
        put(42,"12建筑及装潢材料类");
        put(43,"1农用生产资料类");
        put(44,"2化工产品类");
        put(45,"3机电类");
        put(46,"4木材类");

    }} ;
    private Integer code;
    private String desc;

    GoodsCategory(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
