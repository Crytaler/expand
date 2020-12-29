package com.example.expand.domain;

//import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;

//import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * TODO
 *
 * @author yz.m
 * @version v1.0.0
 * @description:
 * @date 2020/12/8 14:35
 * @see com.example.expand.domain
 */
@Data
@AllArgsConstructor
public class User {

//    @Excel(name = "id")
    private String id;
//    @NotBlank(message = "名称不能为空")
//    @Excel(name = "名称")
    private String name;

    private Date createTime;
}
