package htw.webdev.max.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    private Long questionId;

    private String question;
}
