package bg.softuni.demo.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "homework")
@Data
public class Homework {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime addedOn = LocalDateTime.now();

    @Column(name = "git_address")
    private String gitAddress;

    @ManyToOne
    private User author;

    @ManyToOne
    private Exercise exercise;
}
