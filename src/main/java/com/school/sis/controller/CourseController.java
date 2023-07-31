package com.school.sis.controller;

import com.school.sis.dao.SisDAO;
import com.school.sis.entity.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    SisDAO sisDAO;

    public CourseController(SisDAO sisDAO) {
        this.sisDAO = sisDAO;
    }
    @GetMapping("/list")
    public String listCourses(Model model){
        List<Course> courses = sisDAO.findAllCourses();
        model.addAttribute("courses",courses);
        return "course/list-courses";

    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){
        Course course = new Course();
        model.addAttribute("course",course);
        return "course/course-form";
    }
    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("courseId") int id,Model model){
        Course course = sisDAO.findCourseById(id);
        model.addAttribute("course",course);
        return "course/course-form";
    }
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course){
        sisDAO.updateCourse(course);
        return "redirect:/course/list";
    }
    @PostMapping("/delete")
    public String deleteCourse(@RequestParam("courseId") int id){
        sisDAO.deleteCourseById(id);
        return "redirect:/course/list";
    }

}
