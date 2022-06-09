package com.zhou.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.common.Result;
import com.zhou.pojo.Course;
import com.zhou.pojo.Student;

import java.util.List;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 10:41
 */
public interface StudentService extends IService<Student> {
    Page<Student> search(Integer pageNum, Integer pageSize, String name, String sex, String grade);

    Result<?> add(Student student);

    Result<?> update(Student student);

    Result<?> delete(Long id);

    List<Course> electiveCourses(Long stuId);
}
