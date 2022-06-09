package com.zhou;

import com.zhou.controller.StudentController;
import com.zhou.pojo.Course;
import com.zhou.pojo.Student;
import com.zhou.service.CourseService;
import com.zhou.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentmanagerApplicationTests {

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Test
    public void testStudentSearch(){
        List<Student> students = studentService.search(0, 5, "周", "1", "8").getRecords();
        students.forEach(System.out::println);
    }

    @Test
    public void testStudentAdd(){
        studentService.add(new Student().setGrade(7).setSex(true).setStuName("test01"));
    }


    @Test
    public void testStudentUpdate(){
        studentService.update(new Student().setId(3L).setSex(false));
    }


    @Test
    public void testStudentDelete(){
        System.out.println(studentService.delete(1L).getMsg());
    }


    @Test
    public void testStudentElectiveCourses(){
        studentService.electiveCourses(1L).forEach(System.out::println);
    }

    @Test
    public void testCourseSearch(){
        courseService.search(1,5,"数学").getRecords().forEach(System.out::println);
    }


    @Test
    public void testCourseAdd(){
        courseService.add(new Course().setCourseName("中国近代史").setCredit(2.0F));
    }

    @Test
    public void testCourseUpdate(){
        courseService.update(new Course().setId(1L).setCredit(1.0F));
    }

    @Test
    public void testCourseDelete(){
        System.out.println(courseService.delete(1L).getMsg());
    }
}
