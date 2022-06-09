package com.zhou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.common.Result;
import com.zhou.pojo.Course;
import com.zhou.pojo.Student;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 10:41
 */
public interface CourseService extends IService<Course> {
    Page<Course> search(Integer pageNum, Integer pageSize,String courseName);

    Result<?> add(Course course);

    Result<?> update(Course course);

    Result<?> delete(Long id);
}
