package htw.webdev.max.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Answer {
    @Id
    @GeneratedValue
    private long answerId;

    private String answer;

    private boolean correct;

    public Answer(long answerId, String answer, boolean correct) {
        this.answerId = answerId;
        this.answer = answer;
        this.correct = correct;
    }

    public Answer() {
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
