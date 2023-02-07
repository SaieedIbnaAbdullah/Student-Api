package rest.api.com.example.rest.api.demo.student;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Name must not be empty")
    @Length(min = 5, max = 512, message = "Name must have 5-512 character")
    private String name;


    @NotNull(message = "Age must not be empty")
    @Max(value = 60, message = "Age must be 20-60")
    @Min(value = 20, message = "Age must be 20-60")
    private Integer age;
    @NotEmpty(message = "Email must not be empty" )
    @Email(message = "Must be email type")
    private String email;

    //    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Date of birth must be filled up")
    private String  dob;
    public Student(){}
    public Student(Long id, String name, String email, String dob, Integer age){
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public @NotEmpty(message = "Date of birth must be filled up") String getDob() {
        return dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
