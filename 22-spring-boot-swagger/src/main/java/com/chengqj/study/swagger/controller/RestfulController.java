package com.chengqj.study.swagger.controller;

import com.chengqj.study.swagger.entity.RestEntity;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author chengqj
 * @Date 2020/11/1 16:24
 * @Desc
 */
@Controller
@RequestMapping("/rest")
@Api(tags = "测试接口")
public class RestfulController {
    @ApiOperation(value = "测试rest", notes = "获取restEntity", tags = "测试rest", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "restEntity", value = "测试entity", paramType = "query", required = true)
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = RestEntity.class)
    })
    @PostMapping("/testRestful")
    @ResponseBody
    public RestEntity rest(@RequestBody RestEntity restEntity) {
        restEntity.setAge(20);
        restEntity.setName("asdf");
        return restEntity;
    }


    @ApiOperation(value = "获取人员信息", notes = "获取人员的年龄,姓名", tags = "获取人员信息", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", defaultValue = "张三", paramType = "body", required = true, dataType = "string"),
            @ApiImplicitParam(name = "age", value = "年龄", defaultValue = "19", paramType = "body", required = true, dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "成功", response = RestEntity.class)
    })
    @PostMapping("/testRestful2")
    @ResponseBody
    public RestEntity rest2(String name, Integer age) {
        RestEntity restEntity = new RestEntity();
        restEntity.setName("啊手动阀");
        restEntity.setAge(18);
        return restEntity;
    }


}
