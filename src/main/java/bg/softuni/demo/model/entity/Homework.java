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

    @Pattern(regexp = "^https:\\/github\\.com\\/[A-Za-z0-9_\\-\\.@]{3,30}\\/[A-Za-z0-9_\\-\\.@]{2,30}\\/.*")
    @Column(name = "git_address")
    private String gitAddress;

    @ManyToOne
    private User author;

    @ManyToOne
    private Exercise exercise;
}
