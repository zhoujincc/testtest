package com.zhou.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.common.Result;
import com.zhou.mapper.CourseMapper;
import com.zhou.pojo.Course;
import com.zhou.pojo.Sc;
import com.zhou.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 10:51
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    ScService scService;

    /**
     * 根据课程名查询
     * @param pageNum
     * @param pageSize
     * @param courseName
     * @return
     */
    @Override
    public Page<Course> search(Integer pageNum, Integer pageSize,String courseName) {
        //拼接查询条件
        LambdaQueryWrapper<Course> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like((courseName != null && !"".equals(courseName)),Course::getCourseName,courseName);
        Page<Course> coursePage = new Page(pageNum, pageSize);
        coursePage = page(coursePage, lambdaQueryWrapper);
        return coursePage;
    }

    /**
     * 添加课程
     * @param course
     * @return
     */
    @Override
    public Result<?> add(Course course) {
        if (save(course)){
            return Result.success();
        }else {
            return Result.error(500,"添加失败");
        }
    }

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    @Override
    public Result<?> update(Course course) {
        if (updateById(course)){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }
    }

    /**
     * 删除课程信息
     * @param id
     * @return
     */
    @Override
    public Result<?> delete(Long id) {
        if (ObjectUtil.isNull(id)){
            return Result.error(500,"id有误");
        }
        LambdaQueryWrapper<Sc> scLambdaQueryWrapper = new LambdaQueryWrapper<>();
        scLambdaQueryWrapper.eq(Sc::getCId,id);
        int count = scService.count(scLambdaQueryWrapper);
        if (count == 0){
            delete(id);
            return Result.success("删除成功");
        }
        return Result.error(500,"删除失败，有关联的课程");
    }
}
