package kz.narxoz.demo.service;

import kz.narxoz.demo.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentSevice {
    List<Student> findAllStudents();
    Student saveStudent(Student student);

    void deleteStudent(Long id);

    Student editStudent(Student student, Long id);

    Student findOneById(Long id);
}
