package com.chengqj.study.springbootjackson.convert;

import com.chengqj.study.springbootjackson.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author chengqj
 * @Date 2020/11/2 10:09
 * @Desc 以excel的形式的
 */
@Service
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<User> {

    private static final MediaType EXCEL_TYPE = MediaType.valueOf("application/vnd.ms-excel"); // TODO 自己确定一下MediaType都有什么类型

    public ResponseToXlsConverter() {
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
}
