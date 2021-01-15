package com.erick.common.constant;

/**
 * @author erick
 * @date 2021/1/15 2:55 下午
 * 时间格式化常量类
 */
public interface DateFormatConstant {
    /**
     * 精确到天格式：yyyy-MM-dd
     */
    String DATE_FORMAT_EXACTDAY = "yyyy-MM-dd";

    /**
     * 精确到秒格式：yyyy-MM-dd HH:mm:ss
     */
    String DATE_FORMAT_EXACTSEC = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒格式：yyyy-MM-dd HH:mm:ss:SSS
     */
    String DATE_FORMAT_EXACTMILLINSEC = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 时间格式：HH:mm:ss
     */
    String DATE_FORMAT_EXACTTIME = "HH:mm:ss";

    /**
     * 年格式：yyyy
     */
    String DATE_FORMAT_YEAR = "yyyy";

    /**
     * 月格式：MM
     */
    String DATE_FORMAT_MONTH = "MM";

    /**
     * 天格式：dd
     */
    String DATE_FORMAT_DAY = "dd";

    /**
     * 小时格式：HH
     */
    String DATE_FORMAT_HOUR = "HH";

    /**
     * yyyy年MM月dd日 HH:mm:ss
     */
    String DATE_FORMAT_CHAR_EXACTSEC = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * yyyy年MM月dd日
     */
    String DATE_FORMAT_CHAR_EXACTDAY = "yyyy年MM月dd日";

    /**
     * yyMMdd
     */
    String DATE_FORMAT_SIX = "yyMMdd";

    /**
     * yyyyMMdd
     */
    String DATE_FORMAT_EIGHT = "yyyyMMdd";
}
