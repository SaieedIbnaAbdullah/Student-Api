package rest.api.com.example.rest.api.demo.apiCall;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rest.api.com.example.rest.api.demo.student.Student;
import rest.api.com.example.rest.api.demo.student.StudentEntity;

import java.util.List;

@Service
public class ApiCallService {
    RestTemplate restTemplate = new RestTemplate();

    public List<StudentEntity> getStudentList() {

        return restTemplate.exchange("http://localhost:8080/api/v1/student", HttpMethod.GET, null, List.class).getBody();
    }

    public StudentEntity studentById(long id) {
        return restTemplate.exchange("http://localhost:8080/api/v1/student/" + id, HttpMethod.GET, null, StudentEntity.class).getBody();
    }

    public StudentEntity deleteStudentRecord(long id) {
        return restTemplate.exchange("http://localhost:8080/api/v1/student/" + id, HttpMethod.DELETE, null, StudentEntity.class).getBody();
    }

    public StudentEntity registerNewStudent(Student student) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);
        return restTemplate.exchange("http://localhost:8080/api/v1/student", HttpMethod.POST, entity, StudentEntity.class).getBody();
    }

    public StudentEntity updateStudent(long id, Student student) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Student> entity = new HttpEntity<Student>(student, headers);

        return restTemplate.exchange("http://localhost:8080/api/v1/student/" + id, HttpMethod.PUT, entity, StudentEntity.class).getBody();
    }
}
