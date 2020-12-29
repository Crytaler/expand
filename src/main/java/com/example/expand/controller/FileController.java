package com.example.expand.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.expand.domain.User;
//import com.example.expand.spi.PhraseDoc;
//import com.example.expand.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ServiceLoader;

/**
 * TODO
 *
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2020/12/8 14:34
 * @see com.example.expand.controller
 */
@RestController
@Api
@RequestMapping("/file")
@Slf4j
public class FileController {

    @GetMapping("/expUser")
    @ApiOperation("下载")
    public void expUser(HttpServletResponse response){
//        List<User> userList = new ArrayList<>();
//        User user = new User();
//        user.setId(1L);
//        user.setName("测试");
//
//        ExcelUtils.exportExcel(userList, null, "用户数据", User.class, "用户2020200623.xlsx", response);
        List<User> list = new ArrayList<>();
        list.add(new User("zhangsan", "1231", new Date()));
        list.add(new User("zhangsan1", "1232", new Date()));
        list.add(new User("zhangsan2", "1233", new Date()));
        list.add(new User("zhangsan3", "1234", new Date()));
        list.add(new User("zhangsan4", "1235", new Date()));
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("age", "年龄");
        writer.addHeaderAlias("birthDay", "生日");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(2, "申请人员信息");
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String name = null;
        try {
            name = new String("导出测试".getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);

    }

    @PostMapping("/valid")
    @ApiOperation("ceshi")
    public String expUser(){
        return "success";
    }

}
