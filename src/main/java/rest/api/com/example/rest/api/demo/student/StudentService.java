package rest.api.com.example.rest.api.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private final StudentRepository studentRepository;
    private final ValidationService validationService;

    public StudentService(StudentRepository studentRepository, ValidationService validationService) {
        this.studentRepository = studentRepository;
        this.validationService = validationService;
    }

    public List<StudentEntity> getStudent() {
        return studentRepository.findAll();
    }

    public StudentEntity addNewStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public void deleteStudentById(long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("Id is not exist");
        }
        studentRepository.deleteById(studentId);
    }

    public StudentEntity getStudentbyId(Long studentId) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isEmpty()) {
            throw new IllegalStateException("Record is empty");
        }
        return studentOptional.get();
    }

    public StudentEntity updateStudent(StudentEntity student) {
        StudentEntity exist = studentRepository.findById(student.getId()).get();
        exist.setId(student.getId());
        exist.setAge(student.getAge());
        exist.setName(student.getName());
        exist.setDob(student.getDob());
        exist.setEmail(student.getEmail());
        return studentRepository.save(student);
    }
}
