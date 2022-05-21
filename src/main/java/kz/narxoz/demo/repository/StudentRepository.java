package kz.narxoz.demo.repository;

import kz.narxoz.demo.entity.Student;
import kz.narxoz.demo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> {

}
