package testgroup.security_lesson_1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String group;
}
