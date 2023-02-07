package rest.api.com.example.rest.api.demo.apiCall;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.api.com.example.rest.api.demo.student.Student;
import rest.api.com.example.rest.api.demo.student.StudentEntity;

import java.util.List;

@RestController
@RequestMapping("test/api/call/student")
public class ApiCallController {
    private final ApiCallService apiCallService;

    public ApiCallController(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    @GetMapping
    public List<StudentEntity> getList(StudentEntity studentEntity) {
        return apiCallService.getStudentList();
    }

    @PostMapping
    public StudentEntity addStudent(@Valid @RequestBody Student student) {
        return apiCallService.registerNewStudent(student);

    }

    @GetMapping(path = "{id}")
    public StudentEntity getStudentById(@PathVariable("id") long id) {
        return apiCallService.studentById(id);
    }

    @DeleteMapping(path = "{id}")
    public StudentEntity deleteStudent(@PathVariable("id") long id) {
        return apiCallService.deleteStudentRecord(id);
    }
    @PutMapping(path = "{id}")
    public ResponseEntity<StudentEntity> editStudent(@PathVariable("id") long id, @RequestBody Student student){
        student.setId(id);
        apiCallService.updateStudent(id,student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
