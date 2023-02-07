package rest.api.com.example.rest.api.demo.student;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class ValidationService {
    private final StudentRepository studentRepository;

    public ValidationService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void validateStudent(@Valid Student studentEntity) {
        String date = studentEntity.getDob();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try{
            LocalDate localDate = LocalDate.parse(date, formatter);
        }
        catch (DateTimeParseException e){
            throw new IllegalStateException("Pattern should be: dd-MM-yyyy");
        }

        Optional<StudentEntity> studentOptional = studentRepository.findStudentByEmail(studentEntity.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email already taken...");
        }
    }
}
