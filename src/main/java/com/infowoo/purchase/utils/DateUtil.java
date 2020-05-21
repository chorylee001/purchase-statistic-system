package com.infowoo.purchase.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    private static Object lockObject = new Object();
    private static Map<String,ThreadLocal<SimpleDateFormat>> threadFactory = new HashMap<>();
    private static String ymdhmsPattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期
     * @return
     */
    public static Date getNowTime(){

        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取日期
     * @param min 分钟
     *        min为负数时为前当前时间-min时间
     * @return
     */
    public static Date getTime(int min){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,min);
        return calendar.getTime();
    }

    public static String getTodayYMD(){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return year+"-"+appendZero(month)+"-"+day;
    }

    public static String getTodayYMD(String date){

        try {
            Calendar calendar = Calendar.getInstance();
            if(StringUtils.isNotEmpty(date)){
                calendar.setTime(getFormat().parse(date));
            }
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH)+1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            date=  year+"-"+appendZero(month)+"-"+appendZero(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String appendZero(int num){

        if(num<10){
            return "0"+num;
        }
        return ""+num;
    }

    public static String getymd(String date){

        if(date != null){
            date = date.substring(0,10);
        }
        return date;
    }

    public static String getymdhm(String date,boolean allDate){

        if(date.length() == 19){
            if(allDate){
                date = date.substring(0,16);
            }else{
                date = date.substring(11,16);
            }
        }
        return date;
    }

    public static String getHHmm(String date){

        date = date.trim();
        if(date.length()== 19){
            date = date.substring(11,16);
        }else if(date.length()==8){
            date = date.substring(0,5);
        }
        return date;
    }

    /**
     * 获取日期区间日期
     * @param timeStr 例:2019-01-01 - 2019-12-31
     * @return 日期数组
     */
    public static String[] getTimeRange(String timeStr,String split,boolean allTime){

        String[] timeRange = new String[2];

        if(StringUtils.isEmpty(timeStr)|| StringUtils.isEmpty(split)){
            return timeRange;
        }
        if(timeStr.length()<10){
            throw new IndexOutOfBoundsException("no longer value,can not convert.");
        }

        timeRange[0] = timeStr.substring(0,10).trim();
        timeRange[1] = timeStr.substring(timeStr.indexOf(split,10)+1,timeStr.length()).trim();
        if(allTime){
            timeRange[0] += " 00:00:00";
            timeRange[1] += " 23:59:59";
        }
        return timeRange;
    }

    public static String[] getFullTimeRange(String timeStr,String split,boolean allTime){

        String[] range = timeStr.split(split);
        if(!allTime&&timeStr.length()<30){
            range[0] += ":00:00";
            range[1] += ":00:00";
            return range;
        }
        return timeStr.split(split);
    }

    public static SimpleDateFormat getFormat(final String pattern){

        ThreadLocal<SimpleDateFormat> localPattern = threadFactory.get(pattern);

        if (localPattern == null) {
            synchronized (lockObject) {
                localPattern = threadFactory.get(pattern);
                if (localPattern == null) {
                    localPattern = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    threadFactory.put(pattern, localPattern);
                }
            }
        }
        return localPattern.get();
    }

    public static SimpleDateFormat getFormat(){
        return getFormat(ymdhmsPattern);
    }

}
