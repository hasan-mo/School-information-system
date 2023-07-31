package com.school.sis.dao;

import com.school.sis.entity.*;

import java.util.List;

public interface SisDAO {
    //instructor methods
    void saveInstructor(Instructor instructor);
    Instructor findInstructorById(int id);
    void updateInstructor(Instructor instructor);
    void deleteInstructorById(int id);

    //instructor_detail methods
    InstructorDetail findInstructorDetailById (int id);
    void deleteInstructorDetailById(int id);

    //student methods
    void saveStudent (Student student);
    void updateStudent (Student student);
    Student findStudentById (int id);
    void deleteStudentById(int id);

    //courses methods
    void saveCourse(Course course);
    void updateCourse(Course course);
    Course findCourseById(int id);
    void deleteCourseById(int id);

    List<Course> findCoursesByInstructorId(int id);
    Course findCourseAndReviewsByCourseId(int id);
    Course findCourseAndStudentsByCourseId(int id);
    Student findStudentsAndCoursesByStudentId(int id);
    Instructor findInstuctorByIdJoinFetch(int id);

    List<Instructor> findAllInstructors();
    List<Student> findAllStudents ();
    List<Course> findAllCourses ();

    List<Review> findAllReviews();
    List<InstructorDetail> findAllInstructorDetails ();






}
