package com.chengqj.study.swagger;

import com.chengqj.study.swagger.export.SwaggerExportTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void exportSwagger() throws MalformedURLException {
        SwaggerExportTest swaggerExportTest = new SwaggerExportTest();
        swaggerExportTest.swaggerExport();
    }
}
