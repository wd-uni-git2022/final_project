package htw.webdev.max.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private Long questionId;

    private String question;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Answer> answerList;

    public Question(Long questionId, String question, List<Answer> answerList) {
        this.questionId = questionId;
        this.question = question;
        this.answerList = answerList;
    }

    public Question() {
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
