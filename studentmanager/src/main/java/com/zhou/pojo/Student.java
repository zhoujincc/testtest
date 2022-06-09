package com.zhou.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 10:34
 */

@Data
@TableName("student")
@Accessors(chain = true)
public class Student {
    /**
     * 学生id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生性别
     */
    private Boolean sex;

    /**
     * 年级
     */
    private Integer grade;
}
