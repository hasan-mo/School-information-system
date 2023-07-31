package com.school.sis.dao;

import com.school.sis.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class SisDAOImpl implements SisDAO {

    private EntityManager entityManager;
    @Autowired
    public SisDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);

    }

    @Override
    public Instructor findInstructorById(int id) {

        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void updateInstructor(Instructor instructor) {
        entityManager.merge(instructor);


    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor tempInstructor = entityManager.find(Instructor.class,id);
        List<Course> courses = tempInstructor.getCourses();

        for(Course course: courses){
            course.setInstructor(null);
        }
        entityManager.remove(tempInstructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {

        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail insDet = entityManager.find(InstructorDetail.class, id);

        insDet.getInstructor().setInstructorDetail(null);
        entityManager.remove(insDet);

    }

    @Override
    @Transactional
    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);

    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class,id);


    }

    @Override
    @Transactional
    public void deleteStudentById(int id) {
        Student tempStudent = entityManager.find(Student.class,id);

        entityManager.remove(tempStudent);

    }

    @Override
    @Transactional
    public void saveCourse(Course course) {
        entityManager.persist(course);

    }

    @Override
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);

    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        Course tempCourse = entityManager.find(Course.class,id);
        entityManager.remove(tempCourse);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {


        TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data",Course.class);
        query.setParameter("data",id);

        List<Course> courses = query.getResultList();



        return courses;

    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c"+"JOIN FETCH c.reviews"+"where c.id = :data",Course.class);
        query.setParameter("data",id);
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int id) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c"+"JOIN FETCH c.students"+"where c.id = :data",Course.class);
        query.setParameter("data",id);
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStudentsAndCoursesByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s"+"JOIN FETCH s.courses"+"where s.id = :data",Student.class);
        query.setParameter("data",id);
        Student student = query.getSingleResult();
        return student;
    }

    @Override
    public Instructor findInstuctorByIdJoinFetch(int id) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i"+"JOIN FETCH i.instructorDetail"+"JOIN FETCH i.courses",Instructor.class);
        return null;
    }

    @Override
    public List<Instructor> findAllInstructors() {
        TypedQuery<Instructor> query = entityManager.createQuery("from Instructor",Instructor.class);
        List<Instructor> instructors = query.getResultList();
        return instructors;
    }

    @Override
    public List<Student> findAllStudents() {
        TypedQuery<Student> query = entityManager.createQuery("from Student",Student.class);
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    public List<Course> findAllCourses() {
        TypedQuery<Course> query = entityManager.createQuery("from Course",Course.class);
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public List<Review> findAllReviews() {
        TypedQuery<Review> query = entityManager.createQuery("from Review",Review.class);
        List<Review> reviews = query.getResultList();
        return reviews;
    }

    @Override
    public List<InstructorDetail> findAllInstructorDetails() {
        TypedQuery<InstructorDetail> query = entityManager.createQuery("from InstructorDetail",InstructorDetail.class);
        List<InstructorDetail> instructorsDetail = query.getResultList();
        return instructorsDetail;
    }


}
