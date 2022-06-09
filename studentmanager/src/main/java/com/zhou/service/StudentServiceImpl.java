package com.zhou.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.common.Result;
import com.zhou.mapper.StudentMapper;
import com.zhou.pojo.Course;
import com.zhou.pojo.Sc;
import com.zhou.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 10:42
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    ScService scService;


    @Autowired
    CourseService courseService;

    /**
     * 学生查询为分页条件查询，查询条件如下：
     *
     * 姓名：模糊查询
     * 性别：精确查询
     * 年级：精确查询 学生删除时，需要删除与课程的关联信息
     * @param pageNum
     * @param pageSize
     * @param name
     * @param sex
     * @param grade
     * @return
     */
    @Override
    public Page<Student> search(Integer pageNum, Integer pageSize, String name, String sex, String grade) {
        //拼接查询条件
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();
        //关键词筛选条件
        queryWrapper.like(StrUtil.isNotBlank(name),Student::getStuName,name);
        queryWrapper.eq((StrUtil.isNotBlank(sex) && sex.equals("1")),Student::getSex,true);
        queryWrapper.eq((StrUtil.isNotBlank(sex) && sex.equals("0")),Student::getSex,false);
        queryWrapper.like(StrUtil.isNotBlank(grade),Student::getGrade,grade);

        Page<Student> studentPage = new Page<>(pageNum, pageSize);
        studentPage = page(studentPage, queryWrapper);
        return studentPage;
    }

    /**
     * 添加学生
     * @param student
     * @return
     */
    @Override
    public Result<?> add(Student student) {
        if (save(student)){
            return Result.success();
        }else {
            return Result.error(500,"添加失败");
        }
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @Override
    public Result<?> update(Student student) {
        if (updateById(student)){
            return Result.success();
        }else {
            return Result.error(500,"修改失败");
        }
    }

    /**
     * 删除学生信息
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Result<?> delete(Long id) {
        //删除学生信息
        if (removeById(id)){
            //删除相关联选课的信息
            LambdaQueryWrapper<Sc> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Sc::getStuId,id);
            scService.remove(lambdaQueryWrapper);
            return Result.success("删除成功");
        }
        return Result.error(500,"删除失败");
    }

    /**
     * 获取学生选修的课程信息
     * @param stuId
     * @return
     */
    @Override
    public List<Course> electiveCourses(Long stuId) {
        List<Course> list = new ArrayList<>();
        if (ObjectUtil.isNotNull(stuId)){
            //查询学生的选课信息
            LambdaQueryWrapper<Sc> scLambdaQueryWrapper = new LambdaQueryWrapper<>();
            scLambdaQueryWrapper.eq(Sc::getStuId,stuId);
            List<Sc> scList = scService.list(scLambdaQueryWrapper);

            //通过选课信息获取课程信息
            list = scList.stream().map((item) -> {
                Course course = courseService.getById(item.getCId());
                return course;
            }).collect(Collectors.toList());
        }
        return list;
    }
}
