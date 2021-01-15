package com.erick.common.constant;

/**
 * 常量
 * @author erick
 * @version : 1.0
 * @since  :2019-10-23
 */
public interface Constant {

    /**
     * 身份证号码格式：15位数字，或18位前17位数字，最后一位x
     */
    String IDENTITY_PATTERN_FORMAT = "(^\\d{15}$)|(\\d{17}(?:\\d|x|X)$)";

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
     * 斜杠
     */
    String CHAR_SLASH = "/";

}
