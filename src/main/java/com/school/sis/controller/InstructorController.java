package com.school.sis.controller;

import com.school.sis.dao.SisDAO;
import com.school.sis.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

    private SisDAO sisDAO;


    @Autowired
    public InstructorController(SisDAO sisDAO) {
        this.sisDAO = sisDAO;
    }

    @GetMapping("/list")
    public String listInstructors (Model model){
        List<Instructor> instructors =sisDAO.findAllInstructors();
        model.addAttribute("instructors",instructors);
        return "instructor/list-instructors";

    }
    @GetMapping("/showFormForAdd")
    public String showFormForAdd (Model model){
        Instructor instructor = new Instructor();
        model.addAttribute("instructor",instructor);
        return "instructor/instructor-form";
    }
    @PostMapping("/showFormForUpdate")
    public String showFormForUpdate (@RequestParam("instructorId") int id, Model model){
        Instructor instructor = sisDAO.findInstructorById(id);
        model.addAttribute("instructor",instructor);
        return "instructor/instructor-form";
    }

    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("instructor") Instructor instructor){
        sisDAO.updateInstructor(instructor);
        return "redirect:/instructor/list";
    }

    @PostMapping("/delete")
    public String deleteInstructor (@RequestParam("instructorId") int instructorId){
        sisDAO.deleteInstructorById(instructorId);
        return "redirect:/instructor/list";
    }







}
