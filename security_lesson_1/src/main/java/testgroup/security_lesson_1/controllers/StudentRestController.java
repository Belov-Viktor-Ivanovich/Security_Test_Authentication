package testgroup.security_lesson_1.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;
import testgroup.security_lesson_1.models.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("api/v1/students")
@EnableWebSecurity
public class StudentRestController {
    private List<Student> students = Stream.of(
            new Student(1L,"Alex","7A"),
            new Student(2L,"Alex2","7A"),
            new Student(3L,"Alex3","7A"),
            new Student(4L,"Alex4","7A")
    ).collect(Collectors.toList());

    @GetMapping
    private List<Student>getAll(){
        return students;
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private Student getStudentById(@PathVariable Long id){
        return students.stream().filter(el->el.getId().equals(id))
                .findFirst().orElse(null);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/student")
    private Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    private void deleteById(@PathVariable Long id){

        students.remove(getStudentById(id));
        //students.removeIf(student -> student.getId().equals(id));

    }
}
