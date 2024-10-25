package org.example.sdstest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "students")
public class Student {

    @Id
    private String id;

    @Column(name = "last_name", length = 64, nullable = false)
    private String lastName;

    @Column(name = "first_name", length = 64, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 64, nullable = false)
    private String middleName;

    @Column(name = "group_name", length = 64, nullable = false)
    private String group;

    @Column(name = "average_score", nullable = false, precision = 2, scale = 1)
    private Double averageScore;
}
