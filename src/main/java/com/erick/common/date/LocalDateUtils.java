package com.erick.common.date;

import com.erick.common.constant.Constant;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 基于java8的时间工具类
 * @author pangshaoshan
 * @version 1.0
 * @since 1.8+
 */
public class LocalDateUtils {

    /**
     * 获取当前时间精确到天
     * @return 字符类型时间   yyyy-MM-dd
     */
    public static String getCurrentDate(){
        return LocalDate.now().toString();
    }

    /**
     * 获取当前年
     * @return 年
     */
    public static int getCurrentYear(){
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前月份
     * @return 月
     */
    public static int getCurrentMonths(){
        return LocalDate.now().getMonthValue();
    }

    /**
     * 获取当前月份中的日期
     * @return 月份中日期
     */
    public static int getCurrentDayByMonth(){
        return LocalDate.now().getDayOfMonth();
    }

    /**
     * 获取本周内的时间，即星期几
     * @return 本周内的时间
     */
    public static int getCurrentDayByWeek(){
        return LocalDate.now().getDayOfWeek().getValue();
    }

    /**
     * 获取精确到秒的当前时间
     * @return 当时时间
     */
    public static String getCurrentTimeExactSec(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_EXACTSEC);
        return LocalDateTime.now().format(dateTimeFormatter);
    }

    /**
     * 计算两个日期差
     * @author  erick
     * @since   2020/2/11 8:01 下午
     * @param startTime 开始时间  yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间 yyyy-MM-dd HH:mm:ss
     * @return 相差秒数
     *
     */
    public static int betweenSecond(String startTime , String endTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_EXACTSEC);
        LocalDateTime startDateTime = LocalDateTime.parse(startTime , dateTimeFormatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime , dateTimeFormatter);
        return (int) Duration.between(startDateTime , endDateTime).toMillis()/(1000);
    }

    /**
     * 计算两个时间差
     * @author  erick
     * @since   2020/2/17 11:28 下午
     * @param startTime 开始时间 HH:mm:ss
     * @param endTime 结束时间 HH:mm:ss
     * @return  相差秒数
     *
     */
    public static int betweenTimeSecond(String startTime , String endTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_EXACTTIME);
        LocalTime startDateTime = LocalTime.parse(startTime , dateTimeFormatter);
        LocalTime endDateTime = LocalTime.parse(endTime , dateTimeFormatter);
        return (int)Duration.between(startDateTime , endDateTime).toMillis()/1000;
    }


    /**
     * 获取时间对应的秒
     * @author  erick
     * @since  2020/2/17 10:12 上午
     * @param time 要解析的时间 格式为： HH:mm:ss
     * @return  解析的秒
     *
     */
    public static int parseSecondLocalDateByTime(String time){
        int resultSecond = 0;
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT_EXACTTIME);
            LocalTime localTime = LocalTime.parse(time , dateTimeFormatter);
            int temHour = localTime.getHour();
            int temMinuts = localTime.getMinute();
            int temSecond = localTime.getSecond();
            resultSecond = temHour * 3600 + temMinuts * 60 +temSecond;
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSecond;
    }

}
