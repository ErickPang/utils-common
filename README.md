# utils-common

#### 介绍
常用的工具方法，包括时间工具类,证件号码工具类,http请求工具类,ftp文件上传下载
在线API文档：https://apidoc.gitee.com/erickwhx/utils-common

#### 使用说明

#### 1. 时间工具类 DateUtils
  * strToDateExactDay(String date) : 字符串时间格式化为Date类型，参数精确到天。
  * strToDateExactSec(String date) : 字符串时间格式化为Date类型，参数精确到秒。
  * stringToDate(String date , String dateFormat) : 字符串时间格式化为Date类型，指定格式化格式。
  * getCurrentYear() : 获取当前年。
  * getCurrentMonth() : 获取当前月。
  * getCurrentDay() : 获取当前天。
  * getCurrentHour() : 获取当前小时。
  * getTodayExactMilliSecond() : 获取当前时间精确到毫秒。
  * getTodayExactDayChar() : 获取当前日期(汉字形式)精确到天,格式:yyyy年MM月dd日
  * getTodayExactSecChar() : 获取当前日期(汉字形式)精确到秒,格式:yyyy年MM月dd日 HH:mm:ss
  * getTodyExactDay() : 获取当前日期，格式：yyyy-MM-dd
  * getTodayExactSec() : 获取当前时间,格式：yyyy-MM-dd HH:mm:ss
  * getToday(String dateFormat) : 获取当前时间，格式化自定义。
  * getAnyDay(String dateFormat , int interval) : 获取间隔天数，参数：格式化自定义，间隔时间。
  * getTimeDiff(Date smTime , Date bigTime) : 计算时间差。
  * getTimeDiffStr(String smTime , String bigTime , String dateFormat) : 获取时间差，格式化自定义。
  * getTimeDiffExactDay(String smTime , String bigTime) : 计算时间差，参数时间精确到天，格式：yyyy-MM-dd
  * getTimeDiffExactSec(String smTime , String bigTime) : 计算时间差，参数时间精确到秒，格式：yyyy-MM-dd HH:mm:ss
  * dateToStr(Date date , String dateFormat):将Date类型转换为指定格式字符串时间。
  
#### 2. 证件号码工具类 CertificatesUtils
  * checkIdentityNum ： 校验身份证号码合法性
  * getIdentityBirthDay(String identityNum)：获取身份证号上的出生日期。
  * getAreaNameByIdentityNum(String identityNum)：根据证件号码获取市级行政区域代码
  * getProvinceNameByIdentityNum(String identityNum)：根据证件号码获取对应的省份
#### 3. http请求工具类 HttpUtils
  * httpMethodByStream(String sendMsg , String accessUrl , String requestMthod ,int connectTimeout , int readTimeout)
  http请求指定地址发送数据，可以自定义请求方式，连接超时时间，读取超时时间。
  * sendMsgByPostMethod(String accessUrl , String jsonStr , int connectTimeout, int connectionRequestTimeout , int socketTimeout)  
  发送json格式post请求，可以自定义超时时间。
  * sendMapMsgByPost(String accessUrl , Map<String , String> paraMap, int connectTimeout, int connectionRequestTimeout , int socketTimeout)  
  发送多参数post请求，可以自定义超时时间
  * httpPostStrByDefaultTime(String accessUrl , String jsonStr)
  发送Json格式数据，采用默认超时时间，默认5s
  * httpPostMapByDefaultTime(String accessUrl , Map<String , String> paraMap)
  发送多参数Map格式数据，采用默认超时时间，默认5s  
#### 4. LocalDateUtils时间工具类（java 8）
  * getCurrentDate()
  获取当前时间精确到天
  * getCurrentYear()
  获取当前年
  * getCurrentMonths()
  获取当前月
  * getCurrentDayByMonth()
  获取当前月份中的日
  * getCurrentDayByWeek()
  获取本周内的时间，即星期几
  * getCurrentTimeExactSec()
  获取当前时间精确到秒 yyyy-MM-dd HH:mm:ss
  * betweenSecond(String startTime , String endTime)
  计算两个日期的时间差，返回相差秒数 入参时间格式：yyyy-MM-dd HH:mm:ss
  * betweenTimeSecond(String startTime , String endTime)
  计算两个日期的时间差，返回相差秒数 入参时间格式：HH:mm:ss
  * parseSecondLocalDateByTime(String time)
  获取时间对应的秒数，入参格式：HH:mm:ss
#### 5. FtpUtils ftp工具类
  * FtpUtils(String ip , int port , String userName , String password)  
  构造基本信息，ip：服务器ip，port：服务器端口，userName：用户名，password：密码
  * uploadFile(String encoding , String filePath , String fileName , InputStream inputStream)  
  上传文件，encoding：编码格式；filePath：目标服务器路径；fileName：文件名称，inputStream：上传文件流
  * uploadFile(String encoding , String filePath , String fileName , String sourceFilePath)  
  上传文件，encoding：编码格式；filePath：目标服务器路径；fileName：文件名称，sourceFilePath：上传文件路径
  * downloadFile(String encoding , String filePath , String fileName , String localFilePath)  
  下载文件，encoding：编码格式；filePath：目标服务器路径；fileName：文件名称，localFilePath：本地下载路径
#### 6. JsonParseUtils json格式转换类
  * beanToJson(Object object)  
  将对象转换为Json
  * jsonToObj(String json,Class clazz)  
  将Json转换为对象

#### todo MD5加密、AES加解密、DES加解密、SFTP、https、唯一值生成