package Samgat.TManager.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity

@Table(name = "projects")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "project_completion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate completionDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "project_status")
    private ProjectStatus status;

    @Column(name = "project_priority")
    private int priority;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
    List<Tasks> tasks;


}
