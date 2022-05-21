package kz.narxoz.demo.service.service.empl;

import kz.narxoz.demo.entity.Student;
import kz.narxoz.demo.repository.StudentRepository;
import kz.narxoz.demo.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentsServiceImpl implements StudentSevice {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student editStudent(Student student, Long id) {
        student.setId(id);
        return studentRepository.save(student);
    }

    @Override
    public Student findOneById(Long id){
        return studentRepository.findById(id).orElse(null);
    }
}
