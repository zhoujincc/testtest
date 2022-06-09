package com.zhou.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (课程)
 * @Date: 2022/6/9 10:34
 */
@Data
@TableName("course")
@Accessors(chain = true)
public class Course {

    /**
     * 课程主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 课程学分
     */
    private Float credit;
}
