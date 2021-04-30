# 序列化的配置

### 自定义序列化

0. 序列化为Excel
pom.xml
```xml
<!-- poi -->
        <dependency>
            <groupId>org.apache.poi</groupId>
            <artifactId>poi-ooxml</artifactId>
            <version>4.1.0</version>
        </dependency>

```

1.实现接口并注册为bean

```java
@Service
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<User> {
    ...
}
```

2.实现接口方法

```java
    // 指定返回给浏览器的是Excel文件
    private static final MediaType EXCEL_TYPE = MediaType.valueOf("application/vnd.ms-excel"); // TODO 自己确定一下MediaType都有什么类型
    public ResponseToXlsConverter() {
        // 在构造方法当中指定返回头类型
        super(EXCEL_TYPE);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        // 什么时候用这个处理器
        return User.class == clazz;
    }

    @Override
    protected User readInternal(Class<? extends User> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // @RequestBody的时候用
        return null;
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // #ResponseBody的时候用
        final Workbook workbook = new HSSFWorkbook(); // Excel2003以前（包括2003）的版本，扩展名是.xls
        // final Workbook workbook = new XSSFWorkbook(); // Excel2007的版本，扩展名是.xlsx
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue(user.getName());
        row.createCell(1).setCellValue(user.getAge());
        workbook.write(outputMessage.getBody());

    }
```
3.调用Controller接口,指定@ResponsBody,就会调用序列化器

---

### JackSon序列化

> spingboot 默认使用JacksonFormat,建议大家使用默认

**YML配置文件配置**

```xml
spring:
  jackson:
    date-format: yyyy/MM/dd HH:mm:ss
    time-zone: GMT+8
```
#### 自定义日期参数

日期作为参数接收
```java
// 用@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")指定
public Student get(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date){
    ...
}

```
序列化json返回
```java

/**
 * 自定义配置时间格式使用@JsonFormat
 */
@JsonFormat(pattern = "yyyy_MM_dd HH:mm:ss",timezone = "GMT+8")
private Date birthDate;
```
_均可以打破默认配置以自定义方式处理日期_

####　几个json注解

```java
@JsonPropertyOrder(value = {"address","name"}) // 指定返回json顺序
public class Student {
    
    @JsonProperty("sduname") // 为字段重新指定名字
    private String name;
    
    @JsonIgnore // 忽略一个字段
    private Integer age;
    
    @JsonInclude(JsonInclude.Include.NON_NULL) // 指定为空的时候不要返回
    private String address;

    // 自定义配置时间格式使用@JsonFormat
    @JsonFormat(pattern = "yyyy_MM_dd HH:mm:ss",timezone = "GMT+8")
    private Date birthDate;
}
```

#### 手动转Json
手动需要用到jackson的ObjectMapper

```java
@Test
    void testJackson() throws JsonProcessingException {
        // jacksond的ObjectMapper 转换对象
        ObjectMapper mapper = new ObjectMapper();

        List<User> list = new ArrayList<User>(){{
            add(User.builder().name("lisi").age(15).build());
            add(User.builder().name("zhangsan").age(25).build());
        }};

        // 序列化
        String s = mapper.writeValueAsString(list);
        System.out.println(s);
        // 反序列化对象
        // 注意:对象一定要有无参构造和全参构造
        User user = mapper.readValue("{\"name\":\"lisi\",\"age\":15}", User.class);
        System.out.println(user);
        // 反序列化数组
        List<User> list1 = mapper.readerForListOf(User.class).readValue("[{\"name\":\"lisi\",\"age\":15},{\"name\":\"zhangsan\",\"age\":25}]");
        System.out.println(list1);
    }
```