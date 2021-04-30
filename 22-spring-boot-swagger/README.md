# Springboot 整合 Swagger2

### 优点

1. 前后端分离

2. 代码变,文档变

3. 跨语言性,支持40多种语言

4. Swagger UI呈现出来的是一份可交互式的API文档

5. 将文档规范导入相关的SoapUI,自动的创建自动化测试



#### 命名

 官方: spring-boot-starter-swagger (截至2020年11月1日本文书写,并未有官方提供)

 个人开发: swagger-xxx-spring-boot-starter

 希望大家在开发启动器的时候遵守命名规范

### pom.xml
```xml
<!-- swagger -->
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger2</artifactId>
            <version>2.6.1</version>
        </dependency>
        <dependency>
            <groupId>io.springfox</groupId>
            <artifactId>springfox-swagger-ui</artifactId>
            <version>2.6.1</version>
        </dependency>
```

### config
```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger API文档")
                .description("简单优雅的restfu风格")
                .termsOfServiceUrl("http://www.btbullet.com")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.chengqj.study.swagger"))
                .paths(PathSelectors.regex("/rest/."))
                .build();
    }

}
```
### 注解使用
```java
//Controller
@Api(tags="测试接口")
...
controller
...
@ApiOperation(value = "获取人员信息",notes="获取人员的年龄,姓名",tags="获取人员信息",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", defaultValue = "张三",paramType = "body", required = true,dataType = "string"),
            @ApiImplicitParam(name = "age", value = "年龄", defaultValue = "19",paramType = "body", required = true,dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200,message = "成功",response = RestEntity.class)
    })

// Entity中的注解
@ApiModelProperty(value = "name",example = "我是rest")
```

#　swagger导出

### pom.xml
```xml
<dependency>
            <groupId>io.github.swagger2markup</groupId>
            <artifactId>swagger2markup</artifactId>
            <version>1.3.1</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-core</artifactId>
            <version>1.5.16</version>
        </dependency>
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
            <version>1.5.16</version>
        </dependency>
```

### 生成方式

可以生成markdown,html方式

```java
public void swaggerExport() throws MalformedURLException {
        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN) // 设置生成格式,可以是asciidoc,markdown
                // .withMarkupLanguage(MarkupLanguage.ASCIIDOC) // 设置生成格式,可以是asciidoc,markdown
                .withOutputLanguage(Language.ZH) // 设置中文还是其它语言
                .withPathsGroupedBy(GroupBy.TAGS) // 按tags分组导出,不然每个controller导出一个
                .withGeneratedExamples()
                .withoutInlineSchema()
                .build();

        Swagger2MarkupConverter.from(new URL("http://localhost:8080/v2/api-docs"))
                .withConfig(config)
                .build()
                .toFile(Paths.get("src/main/resources/docs/asciidoc"));
    }
```
将asciidoc导成,依赖一个maven插件

- plugin
```xml
<!--            根据asciidoc生成html文档-->
            <plugin>
                <groupId>org.asciidoctor</groupId>
                <artifactId>asciidoctor-maven-plugin</artifactId>
                <version>1.5.6</version>
                <configuration>
<!--                    asciidoc文件目录-->
                    <sourceDirectory>src/main/resources/docs</sourceDirectory>
<!--                    生成html路径-->
                    <outputDirectory>src/main/resources/html</outputDirectory>
                    <backend>html</backend>
                    <sourceHighlighter>coderay</sourceHighlighter>
                    <attributes>
<!--                        导航栏在左-->
                        <toc>left</toc>
<!--                        显示层级数-->
<!--                        <toclevels>3</toclevels>-->
<!--                        自动打数字序号-->
                        <sectnums>true</sectnums>
                    </attributes>
                </configuration>
            </plugin>
```
然后在maven插件中找Plugins-asciidoctor-asciidoctor:process-asciidoc点击
　