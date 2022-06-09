package com.zhou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhou.common.Result;
import com.zhou.pojo.Course;
import com.zhou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: zhoujinchuan
 * @Description: TODO (用一句话描述一下)
 * @Date: 2022/6/9 11:01
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    /**
     * 根据课程名模糊查询课程
     * @param pageNum
     * @param pageSize
     * @param courseName
     * @return
     */
    @GetMapping("list")
    public Result<Page<Course>> search(@RequestParam(defaultValue = "1") Integer pageNum,
                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                        @RequestParam(defaultValue = "") String courseName){
        Page<Course> Courses = courseService.search(pageNum,pageSize,courseName);
        return Result.success(Courses);
    }

    //用户添加
    @PostMapping()
    public Result<?> add(@RequestBody Course Course){
        return courseService.add(Course);
    }

    //用户更改
    @PutMapping()
    public Result<?> update(@RequestBody Course Course){
        return courseService.update(Course);
    }


    //用户删除
    @DeleteMapping()
    public Result<?> delete(@RequestParam Long id){
        return courseService.delete(id);
    }

}
