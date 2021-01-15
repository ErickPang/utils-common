package com.erick.common.date;

import com.erick.common.constant.Constant;
import com.erick.common.constant.DateFormatConstant;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author : erick
 * @version : 1.0
 * @since 2019-10-13
 */
public class DateUtils {

    /***
     * 将String 转为Date型
     * @param date 字符串时间 yyyy-MM-dd
     * @return Date 将字符串类型转换为Date类型，时间格式要求 yyyy-MM-dd
     * @author : erick
     * @since  : 2019-10-13
     */
    public static Date strToDateExactDay(String date){
        return stringToDate(date, DateFormatConstant.DATE_FORMAT_EXACTDAY);
    }

    /***
     * 带时分秒的String时间转为Date
     * @param date 字符串时间yyyy-MM-dd HH:mm:ss
     * @return 将字符串类型转换为Date类型，时间格式 yyyy-MM-dd HH:mm:ss
     * @author : erick
     * @since : 2019-10-13
     */
    public static Date strToDateExactSec(String date){
        return stringToDate(date , DateFormatConstant.DATE_FORMAT_EXACTSEC);
    }

    /***
     * 字符串时间转为Date类型
     * @param date 时间
     * @param dateFormat 转换格式
     * @return 将字符串类型转换为Date类型
     * @author : erick
     * @since  : 2019-10-13
     */
    public static Date stringToDate(String date , String dateFormat){
        if (null == date || "".equals(date)){
            return null;
        }
        if (null == dateFormat || "".equals(dateFormat)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /***
     * 获取当前年
     * @author erick
     * @return  当前年
     * @since 2019-10-13
     */
    public static String getCurrentYear(){
        return getToday(DateFormatConstant.DATE_FORMAT_YEAR);
    }

    /***
     * 获取当前月
     * @author erick
     * @return  当前月
     * @since 2019-10-13
     */
    public static String getCurrentMonth(){
        return getToday(DateFormatConstant.DATE_FORMAT_MONTH);
    }

    /***
     * 获取当前天
     * @author erick
     * @return 当前天
     * @since  2019-10-13
     */
    public static String getCurrentDay(){
        return getToday(DateFormatConstant.DATE_FORMAT_DAY);
    }

    /***
     * 获取当前小时
     * @author erick
     * @return  当前小时
     * @since 2019-10-13
     */
    public static String getCurrentHour(){
        return getToday(DateFormatConstant.DATE_FORMAT_HOUR);
    }

    /***
     * 获取当前时间精确到毫秒
     * @author erick
     * @return 获取当前时间，时间格式：yyyy-MM-dd HH:mm:ss:SSS
     * @since 2019-10-13
     */
    public static String getTodayExactMilliSecond(){
        return getToday(DateFormatConstant.DATE_FORMAT_EXACTMILLINSEC);
    }

    /***
     * 获取当前时间
     * @author erick
     * @return 当前时间，时间格式：yyyy年MM月dd日
     * @since 2019-10-13
     */
    public static String getTodayExactDayChar(){
        return getToday(DateFormatConstant.DATE_FORMAT_CHAR_EXACTDAY);
    }

    /***
     * 获取当前时间
     * @author  erick
     * @return 当前时间，时间格式： yyyy年MM月dd日 HH:mm:ss
     * @since 2019-10-15
     */
    public static String getTodayExactSecChar(){
        return getToday(DateFormatConstant.DATE_FORMAT_CHAR_EXACTSEC);
    }
    /***
     * 获取当前时间
     * @author erick
     * @return 当前时间  yyyy-MM-dd
     * @since 2019-10-13
     */
    public static String getTodyExactDay(){
        return getToday(DateFormatConstant.DATE_FORMAT_EXACTDAY);
    }

    /***
     * 获取当前时间
     * @author erick
     * @return 当前时间，时间格式： yyyy-MM-dd HH:mm:ss
     * @since 2019-10-13
     */
    public static String getTodayExactSec(){
        return getToday(DateFormatConstant.DATE_FORMAT_EXACTSEC);
    }

    /***
     * 获取当前系统时间
     * @param dateFormat 时间格式
     * @return 当前时间，格式自定义
     * @author erick
     * @since 2019-10-13
     */
    public static String getToday(String dateFormat){
        if (null == dateFormat || "".equals(dateFormat)){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(calendar.getTime());
    }

    /***
     * 获取当前时间任意间隔天数
     * @param dateFormat 时间格式
     * @param interval 间隔天数
     * @return  任意间隔天数的时间，自定义格式
     * @author erick
     * @since 2019-10-13
     */
    public static String getAnyDay(String dateFormat , int interval){
        if (null == dateFormat || "".equals(dateFormat)){
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, interval);
        return new SimpleDateFormat(dateFormat).format(calendar.getTime());
    }

    /***
     * 计算时间相差的天数
     * @param smTime 较小的时间
     * @param bigTime 较大的时间
     * @return 两个时间天数差
     * @author erick
     * @since 2019-10-19
     */
    public static int getTimeDiff(Date smTime , Date bigTime){
        long baseTime = 1000L * 3600 * 24;
        long diffTime = bigTime.getTime() - smTime.getTime();
        long diffDay = diffTime / baseTime;
        if (diffDay > 0){
            diffDay = diffDay + 1;
        }
        return Integer.parseInt(String.valueOf(diffDay));

    }

    /***
     * 计算时间相差天数
     * @param smTime 较小时间
     * @param bigTime 较大时间
     * @param dateFormat 时间格式
     * @return 两个时间天数差，自定义格式。
     * @author erick
     * @since 2019-10-19
     */
    public static int getTimeDiffStr(String smTime , String bigTime , String dateFormat){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        long diffDay = 0L;
        try {
            Date smDate = simpleDateFormat.parse(smTime);
            long smTimeLong = smDate.getTime();
            Date bigDate = simpleDateFormat.parse(bigTime);
            long bigTimeLong = bigDate.getTime();
            long diffTime = bigTimeLong - smTimeLong ;
            diffDay = diffTime / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(diffDay));
    }

    /***
     * 计算时间相差天数
     * @param smTime 较小时间精确到天
     * @param bigTime 较大时间精确到天
     * @return 两个时间的天数差
     * @author erick
     * @since 2019-10-19
     */
    public static int getTimeDiffExactDay(String smTime , String bigTime){
        return getTimeDiffStr(smTime , bigTime , DateFormatConstant.DATE_FORMAT_EXACTDAY);
    }

    /***
     * 计算时间相差天数
     * @param smTime 较小时间精确到秒
     * @param bigTime 较大时间精确到秒
     * @return 两个时间天数差
     * @author erick
     * @since 2019-10-19
     */
    public static int getTimeDiffExactSec(String smTime , String bigTime){
        return getTimeDiffStr(smTime , bigTime , DateFormatConstant.DATE_FORMAT_EXACTSEC);
    }

    /***
     * 将Date类型转换为字符型
     * @param date 要转换的date型日期
     * @param dateFormat 日期格式
     * @return 转换后的字符型日期
     * @author erick
     * @since 2019-10-24
     */
    public static String dateToStr(Date date , String dateFormat){
        if (ObjectUtils.isEmpty(date) || StringUtils.isEmpty(dateFormat)){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

}
