package com.erick.common.certificate;

import com.erick.common.constant.Constant;
import com.erick.common.constant.DateFormatConstant;
import com.erick.common.constant.NumberConstant;
import com.erick.common.date.DateUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 证件相关工具类
 * @author erick
 * @version 1.0
 * @since 2019-10-23
 */
public class CertificatesUtils {


    /**
     * <pre>
     * 省、直辖市代码表：
     *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古
     *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏
     *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东
     *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南
     *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏
     *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆
     *     71 : 台湾
     *     81 : 香港  82 : 澳门
     *     91 : 国外
     * </pre>
     */
    private static String[] cityCode = { "11", "12", "13", "14", "15", "21",
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82", "91" };

    /**
     * 全国市级区域对应关系
     */
    private static Map<String , String> areaCodeMap = new HashMap<String, String>();
    static {
        areaCodeMap.put("110101","北京市东城区");
        areaCodeMap.put("110102","北京市西城区");
        areaCodeMap.put("110105","北京市朝阳区");
        areaCodeMap.put("110106","北京市丰台区");
        areaCodeMap.put("110107","北京市石景山区");
        areaCodeMap.put("110108","北京市海淀区");
        areaCodeMap.put("110109","北京市门头沟区");
        areaCodeMap.put("110111","北京市房山区");
        areaCodeMap.put("110112","北京市通州区");
        areaCodeMap.put("110113","北京市顺义区");
        areaCodeMap.put("110114","北京市昌平区");
        areaCodeMap.put("110115","北京市大兴区");
        areaCodeMap.put("110116","北京市怀柔区");
        areaCodeMap.put("110117","北京市平谷区");
        areaCodeMap.put("110118","北京市密云区");
        areaCodeMap.put("110119","北京市延庆区");
        areaCodeMap.put("120101","天津市和平区");
        areaCodeMap.put("120102","天津市河东区");
        areaCodeMap.put("120103","天津市河西区");
        areaCodeMap.put("120104","天津市南开区");
        areaCodeMap.put("120105","天津市河北区");
        areaCodeMap.put("120106","天津市红桥区");
        areaCodeMap.put("120110","天津市东丽区");
        areaCodeMap.put("120111","天津市西青区");
        areaCodeMap.put("120112","天津市津南区");
        areaCodeMap.put("120113","天津市北辰区");
        areaCodeMap.put("120114","天津市武清区");
        areaCodeMap.put("120115","天津市宝坻区");
        areaCodeMap.put("120116","天津市滨河新区");
        areaCodeMap.put("120117","天津市宁河区");
        areaCodeMap.put("120118","天津市静海区");
        areaCodeMap.put("120119","天津市蓟州区");
        areaCodeMap.put("1301","河北省石家庄市");
        areaCodeMap.put("1302","河北省唐山市");
        areaCodeMap.put("1303","河北省秦皇岛市");
        areaCodeMap.put("1304","河北省邯郸市");
        areaCodeMap.put("1305","河北省邢台市");
        areaCodeMap.put("1306","河北省保定市");
        areaCodeMap.put("1307","河北省张家口市");
        areaCodeMap.put("1308","河北省承德市");
        areaCodeMap.put("1309","河北省沧州市");
        areaCodeMap.put("1310","河北省廊坊市");
        areaCodeMap.put("1311","河北省衡水市");
        areaCodeMap.put("1401","山西省太原市");
        areaCodeMap.put("1402","山西省大同市");
        areaCodeMap.put("1403","山西省阳泉市");
        areaCodeMap.put("1404","山西省长治市");
        areaCodeMap.put("1405","山西省晋城市");
        areaCodeMap.put("1406","山西省朔州市");
        areaCodeMap.put("1407","山西省晋中市");
        areaCodeMap.put("1408","山西省运城市");
        areaCodeMap.put("1409","山西省忻州市");
        areaCodeMap.put("1410","山西省临汾市");
        areaCodeMap.put("1411","山西省吕梁市");
        areaCodeMap.put("1501","内蒙古自治区呼和浩特市");
        areaCodeMap.put("1502","内蒙古自治区包头市");
        areaCodeMap.put("1503","内蒙古自治区乌海市");
        areaCodeMap.put("1504","内蒙古自治区赤峰市");
        areaCodeMap.put("1505","内蒙古自治区通辽市");
        areaCodeMap.put("1506","内蒙古自治区鄂尔多斯市");
        areaCodeMap.put("1507","内蒙古自治区呼伦贝尔市");
        areaCodeMap.put("1508","内蒙古自治区巴彦淖尔市");
        areaCodeMap.put("1509","内蒙古自治区乌兰察布市");
        areaCodeMap.put("1522","内蒙古自治区兴安盟");
        areaCodeMap.put("1525","内蒙古自治区锡林郭勒盟");
        areaCodeMap.put("1529","内蒙古自治区阿拉善盟");
        areaCodeMap.put("2101","辽宁省沈阳市");
        areaCodeMap.put("2102","辽宁省大连市");
        areaCodeMap.put("2103","辽宁省鞍山市");
        areaCodeMap.put("2104","辽宁省抚顺市");
        areaCodeMap.put("2105","辽宁省本溪市");
        areaCodeMap.put("2106","辽宁省丹东市");
        areaCodeMap.put("2107","辽宁省锦州市");
        areaCodeMap.put("2108","辽宁省营口市");
        areaCodeMap.put("2109","辽宁省阜新市");
        areaCodeMap.put("2110","辽宁省辽阳市");
        areaCodeMap.put("2111","辽宁省盘锦市");
        areaCodeMap.put("2112","辽宁省铁岭市");
        areaCodeMap.put("2113","辽宁省朝阳市");
        areaCodeMap.put("2114","辽宁省葫芦岛市");
        areaCodeMap.put("2201","吉林省长春市");
        areaCodeMap.put("2202","吉林省吉林市");
        areaCodeMap.put("2203","吉林省四平市");
        areaCodeMap.put("2204","吉林省辽源市");
        areaCodeMap.put("2205","吉林省通化市");
        areaCodeMap.put("2206","吉林省白山市");
        areaCodeMap.put("2207","吉林省松原市");
        areaCodeMap.put("2208","吉林省白城市");
        areaCodeMap.put("2224","吉林省延边朝鲜族自治州");
        areaCodeMap.put("2301","黑龙江省哈尔滨市");
        areaCodeMap.put("2302","黑龙江省齐齐哈尔市");
        areaCodeMap.put("2303","黑龙江省鸡西市");
        areaCodeMap.put("2304","黑龙江省鹤岗市");
        areaCodeMap.put("2305","黑龙江省双鸭山市");
        areaCodeMap.put("2306","黑龙江省大庆市");
        areaCodeMap.put("2307","黑龙江省伊春市");
        areaCodeMap.put("2308","黑龙江省佳木斯市");
        areaCodeMap.put("2309","黑龙江省七台河市");
        areaCodeMap.put("2310","黑龙江省牡丹江市");
        areaCodeMap.put("2311","黑龙江省黑河市");
        areaCodeMap.put("2312","黑龙江省绥化市");
        areaCodeMap.put("2327","黑龙江省大兴安岭地区");
        areaCodeMap.put("310101","上海市黄浦区");
        areaCodeMap.put("310104","上海市徐汇区");
        areaCodeMap.put("310105","上海市长宁区");
        areaCodeMap.put("310106","上海市静安区");
        areaCodeMap.put("310107","上海市普陀区");
        areaCodeMap.put("310109","上海市普陀区");
        areaCodeMap.put("310110","上海市普陀区");
        areaCodeMap.put("310112","上海市闵行区");
        areaCodeMap.put("310113","上海市宝山区");
        areaCodeMap.put("310114","上海市嘉定区");
        areaCodeMap.put("310115","上海市浦东新区");
        areaCodeMap.put("310116","上海市金山区");
        areaCodeMap.put("310117","上海市松江区");
        areaCodeMap.put("310118","上海市青浦区");
        areaCodeMap.put("310120","上海市奉贤区");
        areaCodeMap.put("310151","上海市崇明区");
        areaCodeMap.put("3201","江苏省南京市");
        areaCodeMap.put("3202","江苏省无锡市");
        areaCodeMap.put("3203","江苏省徐州市");
        areaCodeMap.put("3204","江苏省常州市");
        areaCodeMap.put("3205","江苏省苏州市");
        areaCodeMap.put("3206","江苏省南通市");
        areaCodeMap.put("3207","江苏省连云港市");
        areaCodeMap.put("3208","江苏省淮安市");
        areaCodeMap.put("3209","江苏省盐城市");
        areaCodeMap.put("3210","江苏省扬州市");
        areaCodeMap.put("3211","江苏省镇江市");
        areaCodeMap.put("3212","江苏省泰州市");
        areaCodeMap.put("3213","江苏省宿迁市");
        areaCodeMap.put("3301","浙江省杭州市");
        areaCodeMap.put("3302","浙江省宁波市");
        areaCodeMap.put("3303","浙江省温州市");
        areaCodeMap.put("3304","浙江省嘉兴市");
        areaCodeMap.put("3305","浙江省湖州市");
        areaCodeMap.put("3306","浙江省绍兴市");
        areaCodeMap.put("3307","浙江省金华市");
        areaCodeMap.put("3308","浙江省衢州市");
        areaCodeMap.put("3309","浙江省舟山市");
        areaCodeMap.put("3310","浙江省台州市");
        areaCodeMap.put("3311","浙江省丽水市");
        areaCodeMap.put("3401","安徽省合肥市");
        areaCodeMap.put("3402","安徽省芜湖市");
        areaCodeMap.put("3403","安徽省蚌埠市");
        areaCodeMap.put("3404","安徽省淮南市");
        areaCodeMap.put("3405","安徽省马鞍山市");
        areaCodeMap.put("3406","安徽省马淮北市");
        areaCodeMap.put("3407","安徽省马铜陵市");
        areaCodeMap.put("3408","安徽省安庆市");
        areaCodeMap.put("3410","安徽省黄山市");
        areaCodeMap.put("3411","安徽省滁州市");
        areaCodeMap.put("3412","安徽省阜阳市");
        areaCodeMap.put("3413","安徽省宿州市");
        areaCodeMap.put("3415","安徽省六安市");
        areaCodeMap.put("3416","安徽省亳州市");
        areaCodeMap.put("3417","安徽省池州市");
        areaCodeMap.put("3418","安徽省宣城市");
        areaCodeMap.put("3501","福建省福州市");
        areaCodeMap.put("3502","福建省厦门市");
        areaCodeMap.put("3503","福建省莆田市");
        areaCodeMap.put("3504","福建省三明市");
        areaCodeMap.put("3505","福建省泉州市");
        areaCodeMap.put("3506","福建省漳州市");
        areaCodeMap.put("3507","福建省南平市");
        areaCodeMap.put("3508","福建省龙岩市");
        areaCodeMap.put("3509","福建省宁德市");
        areaCodeMap.put("3601","江西省南昌市");
        areaCodeMap.put("3602","江西省景德镇市");
        areaCodeMap.put("3603","江西省萍乡市");
        areaCodeMap.put("3604","江西省九江市");
        areaCodeMap.put("3605","江西省新余市");
        areaCodeMap.put("3606","江西省鹰潭市");
        areaCodeMap.put("3607","江西省赣州市");
        areaCodeMap.put("3608","江西省吉安市");
        areaCodeMap.put("3609","江西省宜春市");
        areaCodeMap.put("3610","江西省抚州市");
        areaCodeMap.put("3611","江西省上饶市");
        areaCodeMap.put("3701","山东省济南市");
        areaCodeMap.put("3702","山东省青岛市");
        areaCodeMap.put("3703","山东省淄博市");
        areaCodeMap.put("3704","山东省枣庄市");
        areaCodeMap.put("3705","山东省东营市");
        areaCodeMap.put("3706","山东省烟台市");
        areaCodeMap.put("3707","山东省潍坊市");
        areaCodeMap.put("3708","山东省济宁市");
        areaCodeMap.put("3709","山东省泰安市");
        areaCodeMap.put("3710","山东省威海市");
        areaCodeMap.put("3711","山东省日照市");
        areaCodeMap.put("3713","山东省临沂市");
        areaCodeMap.put("3714","山东省德州市");
        areaCodeMap.put("3715","山东省聊城市");
        areaCodeMap.put("3716","山东省滨州市");
        areaCodeMap.put("3717","山东省菏泽市");
        areaCodeMap.put("4101","河南省郑州市");
        areaCodeMap.put("4102","河南省开封市");
        areaCodeMap.put("4103","河南省洛阳市");
        areaCodeMap.put("4104","河南省平顶山市");
        areaCodeMap.put("4105","河南省安阳市");
        areaCodeMap.put("4106","河南省鹤壁市");
        areaCodeMap.put("4107","河南省新乡市");
        areaCodeMap.put("4108","河南省焦作市");
        areaCodeMap.put("4109","河南省濮阳市");
        areaCodeMap.put("4110","河南省许昌市");
        areaCodeMap.put("4111","河南省漯河市");
        areaCodeMap.put("4112","河南省三门峡市");
        areaCodeMap.put("4113","河南省南阳市");
        areaCodeMap.put("4114","河南省商丘市");
        areaCodeMap.put("4115","河南省信阳市");
        areaCodeMap.put("4116","河南省周口市");
        areaCodeMap.put("4117","河南省驻马店市");
        areaCodeMap.put("4201","湖北省武汉市");
        areaCodeMap.put("4202","湖北省黄石市");
        areaCodeMap.put("4203","湖北省十堰市");
        areaCodeMap.put("4205","湖北省宜昌市");
        areaCodeMap.put("4206","湖北省襄阳市");
        areaCodeMap.put("4207","湖北省鄂州市");
        areaCodeMap.put("4208","湖北省荆门市");
        areaCodeMap.put("4209","湖北省孝感市");
        areaCodeMap.put("4210","湖北省荆州市");
        areaCodeMap.put("4211","湖北省黄冈市");
        areaCodeMap.put("4212","湖北省咸宁市");
        areaCodeMap.put("4213","湖北省随州市");
        areaCodeMap.put("4228","湖北省恩施土家族苗族自治州");
        areaCodeMap.put("4301","湖南省长沙市");
        areaCodeMap.put("4302","湖南省株洲市");
        areaCodeMap.put("4303","湖南省湘潭市");
        areaCodeMap.put("4304","湖南省衡阳市");
        areaCodeMap.put("4305","湖南省邵阳市");
        areaCodeMap.put("4306","湖南省岳阳市");
        areaCodeMap.put("4307","湖南省常德市");
        areaCodeMap.put("4308","湖南省张家界市");
        areaCodeMap.put("4309","湖南省益阳市");
        areaCodeMap.put("4310","湖南省郴州市");
        areaCodeMap.put("4311","湖南省永州市");
        areaCodeMap.put("4312","湖南省怀化市");
        areaCodeMap.put("4313","湖南省娄底市");
        areaCodeMap.put("4331","湖南省湘西土家族苗族自治州");
        areaCodeMap.put("4401","广东省广州市");
        areaCodeMap.put("4402","广东省韶关市");
        areaCodeMap.put("4403","广东省深圳市");
        areaCodeMap.put("4404","广东省珠海市");
        areaCodeMap.put("4405","广东省汕头市");
        areaCodeMap.put("4406","广东省佛山市");
        areaCodeMap.put("4407","广东省江门市");
        areaCodeMap.put("4408","广东省湛江市");
        areaCodeMap.put("4409","广东省茂名市");
        areaCodeMap.put("4412","广东省肇庆市");
        areaCodeMap.put("4413","广东省惠州市");
        areaCodeMap.put("4414","广东省梅州市");
        areaCodeMap.put("4415","广东省汕尾市");
        areaCodeMap.put("4416","广东省河源市");
        areaCodeMap.put("4417","广东省阳江市");
        areaCodeMap.put("4418","广东省清远市");
        areaCodeMap.put("4419","广东省东莞市");
        areaCodeMap.put("4420","广东省中山市");
        areaCodeMap.put("4451","广东省潮州市");
        areaCodeMap.put("4452","广东省揭阳市");
        areaCodeMap.put("4453","广东省云浮市");
        areaCodeMap.put("4501","广西壮族自治区南宁市");
        areaCodeMap.put("4502","广西壮族自治区柳州市");
        areaCodeMap.put("4503","广西壮族自治区桂林市");
        areaCodeMap.put("4504","广西壮族自治区梧州市");
        areaCodeMap.put("4505","广西壮族自治区北海市");
        areaCodeMap.put("4506","广西壮族自治区防城港市");
        areaCodeMap.put("4507","广西壮族自治区钦州市");
        areaCodeMap.put("4508","广西壮族自治区贵港市");
        areaCodeMap.put("4509","广西壮族自治区玉林市");
        areaCodeMap.put("4510","广西壮族自治区百色市");
        areaCodeMap.put("4511","广西壮族自治区贺州市");
        areaCodeMap.put("4512","广西壮族自治区河池市");
        areaCodeMap.put("4513","广西壮族自治区来宾市");
        areaCodeMap.put("4514","广西壮族自治区崇左市");
        areaCodeMap.put("4601","海南省海口市");
        areaCodeMap.put("4602","海南省三亚市");
        areaCodeMap.put("4603","海南省三沙市");
        areaCodeMap.put("4604","海南省儋州市");
        areaCodeMap.put("500101","重庆市万州区");
        areaCodeMap.put("500102","重庆市涪陵区");
        areaCodeMap.put("500103","重庆市渝中区");
        areaCodeMap.put("500104","重庆市大渡口区");
        areaCodeMap.put("500105","重庆市江北区");
        areaCodeMap.put("500106","重庆市沙坪坝区");
        areaCodeMap.put("500107","重庆市九龙坡区");
        areaCodeMap.put("500108","重庆市南岸区");
        areaCodeMap.put("500109","重庆市北碚区");
        areaCodeMap.put("500110","重庆市綦江区");
        areaCodeMap.put("500111","重庆市大足区");
        areaCodeMap.put("500112","重庆市渝北区");
        areaCodeMap.put("500113","重庆市巴南区");
        areaCodeMap.put("500114","重庆市黔江区");
        areaCodeMap.put("500115","重庆市长寿区");
        areaCodeMap.put("500116","重庆市江津区");
        areaCodeMap.put("500117","重庆市合川区");
        areaCodeMap.put("500118","重庆市永川区");
        areaCodeMap.put("500119","重庆市南川区");
        areaCodeMap.put("500120","重庆市璧山区");
        areaCodeMap.put("500151","重庆市铜梁区");
        areaCodeMap.put("500152","重庆市潼南区");
        areaCodeMap.put("500153","重庆市荣昌区");
        areaCodeMap.put("500154","重庆市开州区");
        areaCodeMap.put("500155","重庆市梁平区");
        areaCodeMap.put("500156","重庆市武隆区");
        areaCodeMap.put("500229","重庆市城口县");
        areaCodeMap.put("500230","重庆市丰都县");
        areaCodeMap.put("500231","重庆市垫江县");
        areaCodeMap.put("500233","重庆市忠县");
        areaCodeMap.put("500235","重庆市云阳县");
        areaCodeMap.put("500236","重庆市奉节县");
        areaCodeMap.put("500237","重庆市巫山县");
        areaCodeMap.put("500238","重庆市巫溪县");
        areaCodeMap.put("500240","重庆市石柱土家族自治县");
        areaCodeMap.put("500241","重庆市秀山土家族苗族自治县");
        areaCodeMap.put("500242","重庆市阳土家族苗族自治县");
        areaCodeMap.put("500243","重庆市彭水苗族土家族自治县");
        areaCodeMap.put("5101","四川省成都市");
        areaCodeMap.put("5103","四川省自贡市");
        areaCodeMap.put("5104","四川省攀枝花市");
        areaCodeMap.put("5105","四川省泸州市");
        areaCodeMap.put("5106","四川省德阳市");
        areaCodeMap.put("5107","四川省绵阳市");
        areaCodeMap.put("5108","四川省广元市");
        areaCodeMap.put("5109","四川省遂宁市");
        areaCodeMap.put("5110","四川省内江市");
        areaCodeMap.put("5111","四川省乐山市");
        areaCodeMap.put("5113","四川省南充市");
        areaCodeMap.put("5114","四川省眉山市");
        areaCodeMap.put("5115","四川省宜宾市");
        areaCodeMap.put("5116","四川省广安市");
        areaCodeMap.put("5117","四川省达州市");
        areaCodeMap.put("5118","四川省雅安市");
        areaCodeMap.put("5119","四川省巴中市");
        areaCodeMap.put("5120","四川省资阳市");
        areaCodeMap.put("5132","阿坝藏族羌族自治州");
        areaCodeMap.put("5133","甘孜藏族自治州");
        areaCodeMap.put("5134","凉山彝族自治州");
        areaCodeMap.put("5201","贵阳市");
        areaCodeMap.put("5202","六盘水市");
        areaCodeMap.put("5203","遵义市");
        areaCodeMap.put("5204","安顺市");
        areaCodeMap.put("5205","毕节市");
        areaCodeMap.put("5206","铜仁市");
        areaCodeMap.put("5223","黔西南布依族苗族自治州");
        areaCodeMap.put("5226","黔东南苗族侗族自治州");
        areaCodeMap.put("5227","黔南布依族苗族自治州");
        areaCodeMap.put("5301","昆明市");
        areaCodeMap.put("5303","曲靖市");
        areaCodeMap.put("5304","玉溪市");
        areaCodeMap.put("5305","保山市");
        areaCodeMap.put("5306","昭通市");
        areaCodeMap.put("5307","丽江市");
        areaCodeMap.put("5308","普洱市");
        areaCodeMap.put("5309","临沧市");
        areaCodeMap.put("5323","楚雄彝族自治州");
        areaCodeMap.put("5325","红河哈尼族彝族自治州");
        areaCodeMap.put("5326","文山壮族苗族自治州");
        areaCodeMap.put("5328","西双版纳傣族自治州");
        areaCodeMap.put("5329","大理白族自治州");
        areaCodeMap.put("5331","德宏傣族景颇族自治州");
        areaCodeMap.put("5333","怒江傈僳族自治州");
        areaCodeMap.put("5334","迪庆藏族自治州");
        areaCodeMap.put("5401","西藏自治区拉萨市");
        areaCodeMap.put("5402","西藏自治区日喀则市");
        areaCodeMap.put("5403","西藏自治区昌都市");
        areaCodeMap.put("5404","西藏自治区林芝市");
        areaCodeMap.put("5405","西藏自治区山南市");
        areaCodeMap.put("5406","西藏自治区那曲市");
        areaCodeMap.put("5425","西藏自治区阿里地区");
        areaCodeMap.put("6101","陕西省西安市");
        areaCodeMap.put("6102","陕西省铜川市");
        areaCodeMap.put("6103","陕西省宝鸡市");
        areaCodeMap.put("6104","陕西省咸阳市");
        areaCodeMap.put("6105","陕西省渭南市");
        areaCodeMap.put("6106","陕西省延安市");
        areaCodeMap.put("6107","陕西省汉中市");
        areaCodeMap.put("6108","陕西省榆林市");
        areaCodeMap.put("6109","陕西省安康市");
        areaCodeMap.put("6110","陕西省商洛市");
        areaCodeMap.put("6201","甘肃省兰州市");
        areaCodeMap.put("6202","甘肃省嘉峪关市");
        areaCodeMap.put("6203","甘肃省金昌市");
        areaCodeMap.put("6204","甘肃省白银市");
        areaCodeMap.put("6205","甘肃省天水市");
        areaCodeMap.put("6206","甘肃省武威市");
        areaCodeMap.put("6207","甘肃省张掖市");
        areaCodeMap.put("6208","甘肃省平凉市");
        areaCodeMap.put("6209","甘肃省酒泉市");
        areaCodeMap.put("6210","甘肃省庆阳市");
        areaCodeMap.put("6211","甘肃省定西市");
        areaCodeMap.put("6212","甘肃省陇南市");
        areaCodeMap.put("6229","甘肃省临夏回族自治州");
        areaCodeMap.put("6230","甘肃省甘南藏族自治州");
        areaCodeMap.put("6301","青海省西宁市");
        areaCodeMap.put("6302","青海省海东市");
        areaCodeMap.put("6322","青海省海北藏族自治州");
        areaCodeMap.put("6323","青海省黄南藏族自治州");
        areaCodeMap.put("6325","青海省海南藏族自治州");
        areaCodeMap.put("6326","青海省果洛藏族自治州");
        areaCodeMap.put("6327","青海省玉树藏族自治州");
        areaCodeMap.put("6328","青海省海西蒙古族藏族自治州");
        areaCodeMap.put("6401","宁夏回族自治区银川市");
        areaCodeMap.put("6402","宁夏回族自治区石嘴山市");
        areaCodeMap.put("6403","宁夏回族自治区吴忠市");
        areaCodeMap.put("6404","宁夏回族自治区固原市");
        areaCodeMap.put("6405","宁夏回族自治区中卫市");
        areaCodeMap.put("6501","新疆维吾尔自治区乌鲁木齐市");
        areaCodeMap.put("6502","新疆维吾尔自治区克拉玛依市");
        areaCodeMap.put("6504","新疆维吾尔自治区吐鲁番市");
        areaCodeMap.put("6505","新疆维吾尔自治区哈密市");
        areaCodeMap.put("6523","新疆维吾尔自治区昌吉回族自治州");
        areaCodeMap.put("6527","新疆维吾尔自治区博尔塔拉蒙古自治州");
        areaCodeMap.put("6528","新疆维吾尔自治区巴音郭楞蒙古自治州");
        areaCodeMap.put("6529","新疆维吾尔自治区阿克苏地区");
        areaCodeMap.put("6530","新疆维吾尔自治区克孜勒苏柯尔克孜自治州");
        areaCodeMap.put("6531","新疆维吾尔自治区喀什地区");
        areaCodeMap.put("6532","新疆维吾尔自治区和田地区");
        areaCodeMap.put("6540","新疆维吾尔自治区伊犁哈萨克自治州");
        areaCodeMap.put("6542","新疆维吾尔自治区塔城地区");
        areaCodeMap.put("6543","新疆维吾尔自治区阿勒泰地区");
        areaCodeMap.put("659001","新疆维吾尔自治区石河子市");
        areaCodeMap.put("659002","新疆维吾尔自治区阿拉尔市");
        areaCodeMap.put("659003","新疆维吾尔自治区图木舒克市");
        areaCodeMap.put("659004","新疆维吾尔自治区五家渠市");
        areaCodeMap.put("659005","新疆维吾尔自治区北屯市");
        areaCodeMap.put("659006","新疆维吾尔自治区铁门关市");
        areaCodeMap.put("659007","新疆维吾尔自治区双河市");
        areaCodeMap.put("659008","新疆维吾尔自治区可克达拉市");
        areaCodeMap.put("659009","新疆维吾尔自治区昆玉市");
        areaCodeMap.put("7100","台湾省");
        areaCodeMap.put("8100","香港特别行政区");
        areaCodeMap.put("8200","澳门特别行政区");

    }

    /**
     * 全国省份编码
     */
    private static Map<String , String> provinceCodeMap = new HashMap<String, String>();
    static {
        provinceCodeMap.put("11", "北京");
        provinceCodeMap.put("12", "天津");
        provinceCodeMap.put("13", "河北");
        provinceCodeMap.put("14", "山西");
        provinceCodeMap.put("15", "内蒙古");
        provinceCodeMap.put("21", "辽宁");
        provinceCodeMap.put("22", "吉林");
        provinceCodeMap.put("23", "黑龙江");
        provinceCodeMap.put("31", "上海");
        provinceCodeMap.put("32", "江苏");
        provinceCodeMap.put("33", "浙江");
        provinceCodeMap.put("34", "安徽");
        provinceCodeMap.put("35", "福建");
        provinceCodeMap.put("36", "江西");
        provinceCodeMap.put("37", "山东");
        provinceCodeMap.put("41", "河南");
        provinceCodeMap.put("42", "湖北");
        provinceCodeMap.put("43", "湖南");
        provinceCodeMap.put("44", "广东");
        provinceCodeMap.put("45", "广西");
        provinceCodeMap.put("46", "海南");
        provinceCodeMap.put("50", "重庆");
        provinceCodeMap.put("51", "四川");
        provinceCodeMap.put("52", "贵州");
        provinceCodeMap.put("53", "云南");
        provinceCodeMap.put("54", "西藏");
        provinceCodeMap.put("61", "陕西");
        provinceCodeMap.put("62", "甘肃");
        provinceCodeMap.put("63", "青海");
        provinceCodeMap.put("64", "宁夏");
        provinceCodeMap.put("65", "新疆");
        provinceCodeMap.put("71", "台湾");
        provinceCodeMap.put("81", "香港");
        provinceCodeMap.put("82", "澳门");
        provinceCodeMap.put("91", "国外");
    }
    /**
     * 每位加权因子
     */
    private static int[] power = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,8, 4, 2 };


    /***
     * 校验身份证号码合法性
     * @param identityNum 身份证号码
     * @return 是否合法，true : 合法，false : 不合法
     * @author erick
     * @since  : 2019-10-24
     */
    public static boolean checkIdentityNum(String identityNum){

        if(StringUtils.isEmpty(identityNum)){
            return false;
        }
        /**
         * 校验长度
         */
        identityNum = StringUtils.trim(identityNum);
        if (identityNum.length() != NumberConstant.IDENTITY_LEN_15
                && identityNum.length() != NumberConstant.IDENTITY_LEN_18){
            return false;
        }
        /**
         * 校验只能位数字，新身份证最后一位可以为字母
         */
        Pattern pattern = Pattern.compile(Constant.IDENTITY_PATTERN_FORMAT);
        Matcher matcher = pattern.matcher(identityNum);
        if (!matcher.find()){
            return false;
        }
        //校验省份
        if (!checkProvinceId(identityNum)){
            return false;
        }
        if (identityNum.length() == NumberConstant.IDENTITY_LEN_15){
            return validate15IdCard(identityNum);
        }
        return validate18IdCard(identityNum);
    }

    /***
     * 校验省份
     * @param identityNum 身份证号码
     * @author erick
     * @since  2019-10-23
     */
    private static boolean checkProvinceId(String identityNum){
        String provinceId = identityNum.substring(0 , 2);
        for (String proId : cityCode){
            if (provinceId.equals(proId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 校验15位身份证号码
     * @param identityNum 身份证号码
     * @return 15位身份证号码合法性，true : 合法 ，false : 不合法
     * @author erick
     * @since 2019-10-24
     */
    private static boolean validate15IdCard(String identityNum){
        String birthDay = identityNum.substring(6 , 12);
        return checkBirthDay(birthDay , DateFormatConstant.DATE_FORMAT_SIX);
    }

    /**
     * 校验18位身份证号码
     * @param identityNum 身份证号码
     * @return 18位身份证号码合法性，true : 合法 ，false : 不合法
     * @author erick
     * @since 2019-10-24
     */
    private static boolean validate18IdCard(String identityNum){
        String birthDay = identityNum.substring(6 , 14);
        if (!checkBirthDay(birthDay , DateFormatConstant.DATE_FORMAT_EIGHT)){
            return false;
        }

        String idCard17 = identityNum.substring(0, 17);
        // 获取第18位
        String idCard18 = identityNum.substring(17, 18);

        char[] idCard17Char = idCard17.toCharArray();

        int[] idCard17Int = converCharToInt(idCard17Char);
        int sum17 = getPowerSum(idCard17Int);
        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        return idCard18.equalsIgnoreCase(checkCode);
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     * @param sum17 前17位加权后的和
     * @return 校验码
     * @author erick
     * @since 2019-10-24
     */
    private static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
            default:
        }
        return checkCode;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param idCard17Int 前17位的整数
     * @return 加权和值
     * @author erick
     * @since 2019-10-24
     */
    private static int getPowerSum(int[] idCard17Int) {
        int sum = 0;
        if (power.length != idCard17Int.length) {
            return sum;
        }
        for (int i = 0; i < idCard17Int.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + idCard17Int[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param idCardChar 前17位号码
     * @return 整型数组
     * @throws NumberFormatException 数组格式化异常
     * @author erick
     * @since 2019-10-24
     */
    private static int[] converCharToInt(char[] idCardChar) throws NumberFormatException {
        int[] idCardInt = new int[idCardChar.length];
        int tmpInt = 0;
        for (char temp : idCardChar) {
            idCardInt[tmpInt++] = Integer.parseInt(String.valueOf(temp));
        }
        return idCardInt;
    }

    /**
     * 校验生日是否有效
     * @param birthDay 生日日期
     * @param dateFormat 日期格式
     * @return 生日是否正确，true : 正确 , false : 错误
     * @author erick
     * @since 2019-10-24
     */
    private static boolean checkBirthDay(String birthDay , String dateFormat){
        Date birthDate = DateUtils.stringToDate(birthDay , dateFormat);
        if (ObjectUtils.isEmpty(birthDate)){
            return false;
        }
        String tmpDate = DateUtils.dateToStr(birthDate , dateFormat);
        if (StringUtils.isEmpty(tmpDate)){
            return false;
        }
        return birthDay.equals(tmpDate);
    }

    /**
     * 获取身份证号码中的出生日期
     * @param identityNum 身份证号码
     * @return 出生日期
     * @author : erick
     * @since  : 2019-10-24
     */
    public static String getIdentityBirthDay(String identityNum){
        if (StringUtils.isEmpty(identityNum)){
            return "";
        }
        if (NumberConstant.IDENTITY_LEN_15 != identityNum.length()
                && NumberConstant.IDENTITY_LEN_18 != identityNum.length()){
            return "";
        }
        if (NumberConstant.IDENTITY_LEN_15 == identityNum.length()){
            String birthDayYear = "19" + identityNum.substring(6 , 8);
            String birthDayMonth = identityNum.substring(8 , 10);
            String birthDay = identityNum.substring(10 , 12);
            return birthDayYear + "-" + birthDayMonth + "-" + birthDay;
        }
        String birthDayYear = identityNum.substring(6 , 10);
        String birthDayMonth = identityNum.substring(10 , 12);
        String birthDay = identityNum.substring(12 , 14);
        return birthDayYear + "-" + birthDayMonth + "-" + birthDay;
    }

    /**
     * 根据证件号码获取市级行政区域代码
     * @author erick
     * @since 2019-11-17
     * @param identityNum 证件号码
     * @return 返回对应市级名称
     */
    public static String getAreaNameByIdentityNum(String identityNum){
        if (StringUtils.isEmpty(identityNum)){
            return "证件号码不能为空";
        }
        String areaCode = identityNum.substring(0 ,4);
        if (areaCode.startsWith(NumberConstant.AREACODE_PREFIX1101) || areaCode.startsWith(NumberConstant.AREACODE_PREFIX1201) || areaCode.startsWith(NumberConstant.AREACODE_PREFIX3101)
            ||areaCode.startsWith(NumberConstant.AREACODE_PREFIX5001) || areaCode.startsWith(NumberConstant.AREACODE_PREFIX5002) || areaCode.startsWith(NumberConstant.AREACODE_PREFIX6590)){
            areaCode = identityNum.substring(0 , 6);
        }
        String areaName = areaCodeMap.get(areaCode);
        if (StringUtils.isEmpty(areaName)){
            return "不存在该市级区域或截取错误";
        }
        return areaName;
    }

    /**
     * 根据证件号码获取对应的省份
     * @author erick
     * @since 2019-11-17
     * @param identityNum 证件号码
     * @return 返回对应省份
     */
    public static String getProvinceNameByIdentityNum(String identityNum){
        if (StringUtils.isEmpty(identityNum)){
            return "证件号码不能为空";
        }
        String provinceCode = identityNum.substring(0,2);
        String  provinceName = provinceCodeMap.get(provinceCode);
        if (StringUtils.isEmpty(provinceName)){
            return "不存在该省份或截取错误";
        }
        return provinceName;
    }

}
