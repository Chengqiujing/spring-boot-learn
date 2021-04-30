package com.chengqj.study.swagger.export;

import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Language;
import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @Author chengqj
 * @Date 2020/11/1 17:58
 * @Desc
 */
public class SwaggerExportTest {

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
}
