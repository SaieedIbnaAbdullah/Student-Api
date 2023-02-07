package rest.api.com.example.rest.api.demo.student;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.com.example.rest.api.demo.student.StudentEntity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {
    @Autowired
    private final StudentService studentService;

    private final ValidationService validationService;

    public StudentController(StudentService studentService, ValidationService validationService) {
        this.studentService = studentService;
        this.validationService = validationService;
    }
 
    public StudentEntity mapToMyEntity(Student student) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(student.getId());
        String date = student.getDob();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate dob = LocalDate.parse(date, formatter);
        studentEntity.setDob(dob);

        studentEntity.setAge(student.getAge());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setName(student.getName());

        return studentEntity;
    }
//    public StudentEntity mapToMyEntityForUpdate(long id, Student student) {
//        StudentEntity studentEntity = new StudentEntity();
//        String date = student.getDob();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
//        LocalDate dob = LocalDate.parse(date, formatter);
//        studentEntity.setDob(dob);
//
//        studentEntity.setAge(student.getAge());
//        studentEntity.setEmail(student.getEmail());
//        studentEntity.setName(student.getName());
//
//        return studentEntity;
//    }

    @GetMapping
    public List<StudentEntity> getStudent() {
        return studentService.getStudent();
    }

    @PostMapping
    public ResponseEntity<StudentEntity> registerNewStudent(@Valid @RequestBody Student student) {
        validationService.validateStudent(student);
        StudentEntity student1 = studentService.addNewStudent(mapToMyEntity(student));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long studentId) {
        studentService.deleteStudentById(studentId);
    }

    @GetMapping(path = "{studentId}")
    public StudentEntity getStudentById(@PathVariable("studentId") long studentId) {
        return studentService.getStudentbyId(studentId);
    }

    @PutMapping(path = "{studentId}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable("studentId") long studentId, @RequestBody Student student) {
        student.setId(studentId);
        studentService.updateStudent(mapToMyEntity(student));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
