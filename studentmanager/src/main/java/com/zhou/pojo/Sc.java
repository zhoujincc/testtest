package com.zhou.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 11:43
 */
@Data
@TableName("sc")
@Accessors(chain = true)
public class Sc {

    /**
     * 学生id
     */
    private Long stuId;

    /**
     * 课程id
     */
    private Long cId;


    /**
     * 分数
     */
    private Integer score;
}
