package htw.webdev.max.model;

import javax.persistence.*;
import java.util.List;


@Entity
public class Quiz {

    @Id
    @GeneratedValue
    private Long quizId;

    private String quizName;

    public Quiz(Long quizId, String quizName, List<Question> questionList) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questionList = questionList;
    }

    public Quiz() {
    }

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Question> questionList;

    @Override
    public String toString() {
        return String.format("quizId %d; quizName %s", quizId, quizName);
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
