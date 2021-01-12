package com.erick.common.constant;

/**
 * 常量
 * @author erick
 * @version : 1.0
 * @since  :2019-10-23
 */
public interface Constant {

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

    /**
     * 数字15
     */
    int IDENTITY_LEN_15 = 15;

    /**
     * 数字18
     */
    int IDENTITY_LEN_18 = 18;

    /**
     * 身份证号码格式：15位数字，或18位前17位数字，最后一位x
     */
    String IDENTITY_PATTERN_FORMAT = "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)";

    /**
     * 市级区域对应前缀代码-北京市
     */
    String AREACODE_PREFIX1101 = "1101";

    /**
     * 市级区域对应前缀代码-天津市
     */
    String AREACODE_PREFIX1201 = "1201";

    /**
     * 市级区域对应前缀代码-上海市
     */
    String AREACODE_PREFIX3101 = "3101";

    /**
     * 市级区域对应前缀代码-重庆市
     */
    String AREACODE_PREFIX5001 = "5001";

    /**
     * 市级区域对应前缀代码-重庆市
     */
    String AREACODE_PREFIX5002 = "5002";

    /**
     * 市级区域对应前缀代码-新疆维吾尔自治区
     */
    String AREACODE_PREFIX6590 = "6590";

    /**
     * 字符编码UTF-8
     */
    String UTF_8 = "UTF-8";

    /**
     * 字符编码GBK
     */
    String GBK = "GBK";

    /**
     * 字符编码ISO_8859_1
     */
    String ISO_8859_1 = "iso-8859-1";
    /**
     * 整型数字5000
     */
    int NUM_INT_5000 = 5000;
    /**
     * 整型数字200
     */
    int NUM_INT_200 = 200;

    /**
     * 整型数字0
     */
    int NUM_INT_0 = 0;
    /**
     * 斜杠
     */
    String CHAR_SLASH = "/";

}
