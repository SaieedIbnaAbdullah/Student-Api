package rest.api.com.example.rest.api.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findStudentByEmail(String email);
    Optional<StudentEntity> findStudentById(@Param("id")Long id);
}