package com.zhou.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.common.Result;
import com.zhou.pojo.Course;
import com.zhou.pojo.Sc;
import com.zhou.pojo.Student;
import com.zhou.service.ScService;
import com.zhou.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 11:01
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    ScService scService;


    /**
     * 学生选修课程
     * @param stuId
     * @param cId
     * @return
     */
    @PostMapping("/elective")
    public Result elective(@RequestParam Long stuId,@RequestParam Long cId){
        if (ObjectUtil.isNotNull(stuId) && ObjectUtil.isNotNull(cId)){
            scService.save(new Sc().setStuId(stuId).setCId(cId));
            return Result.success("选修成功");
        }
        return Result.error(500,"信息有误");
    }

    @GetMapping("/electiveCourses")
    public Result<List<Course>> electiveCourses(Long stuId){
        List<Course> courses = studentService.electiveCourses(stuId);
        return Result.success(courses);
    }


    /**
     *
     *  姓名：模糊查询
     *  性别：精确查询
     *  年级：精确查询 学生删除时，需要删除与课程的关联信息
     *
     * @param pageNum 分页数
     * @param pageSize 分页大小
     * @param name  姓名
     * @param sex 性别
     * @param grade 年级
     * @return
     */
    @GetMapping("list")
    public Result<Page<Student>> search(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String name,
                                        @RequestParam(defaultValue = "") String sex,
                                        @RequestParam(defaultValue = "") String grade){
        Page<Student> students = studentService.search(pageNum,pageSize,name,sex,grade);
        return Result.success(students);
    }

    //用户添加
    @PostMapping()
    public Result<?> add(@RequestBody Student student){
        return studentService.add(student);
    }

    //用户更改
    @PutMapping()
    public Result<?> update(@RequestBody Student student){
        return studentService.update(student);
    }


    //用户删除
    @DeleteMapping()
    public Result<?> delete(@RequestParam Long id){
        return studentService.delete(id);
    }

}
