package com.erick.common.jsonparse;

import com.erick.common.constant.Constant;
import com.erick.common.constant.DateFormatConstant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;

/**
* json转换类
* @author  erick
* @date   2020/4/22 5:46 下午
*
*/
@Slf4j
public class JsonParseUtils {

    /**
    * 将对象转为json字符串
    * @author  erick
    * @date  2020/4/22 6:00 下午
    * @param object 传入对象
    * @return 转换后的字符串
    * @exception Exception
    *
    */
    public static String beanToJson(Object object) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String parseJson = null;
        try {
            //忽略空值
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            mapper.setDateFormat(new SimpleDateFormat(DateFormatConstant.DATE_FORMAT_EXACTSEC));
            parseJson = mapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("对象转Json发生异常");
        }
        return parseJson;
    }
    /**
    * json转换为对象
    * @author  erick
    * @date  2020/4/22 6:12 下午
    * @param json 待转换的字符串
    * @param clazz 对应的转换对象
    * @return 转换对象
    * @exception  Exception
    *
    */
    public static <T> T jsonToObj(String json,Class<T> clazz) throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        T tmpT=null;
        try {
            mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, Boolean.TRUE);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, Boolean.TRUE);
            tmpT=mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("json转换为对象发生异常"+e);
        }
        return tmpT;
    }
    /**
     * 获取对应节点的值
     * @author  erick
     * @date  2021/1/12  6:57 下午
     * @param jsonStr 源json数据
     * @param nodeName 节点名称
     * @return 对应节点的值
     *
     */
    public static String getString(String jsonStr , String nodeName) throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonStr);
        return jsonNode.findValue(nodeName).asText();
    }
}
