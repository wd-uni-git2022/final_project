package htw.webdev.max.config;

import htw.webdev.max.model.Answer;
import htw.webdev.max.model.AppUser;
import htw.webdev.max.model.Question;
import htw.webdev.max.model.Quiz;
import htw.webdev.max.service.AuthService;
import htw.webdev.max.service.QuizService;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Startup
@ApplicationScoped
public class ExampleQuizCreator {

    public ExampleQuizCreator(QuizService quizService){

        Answer falseAnswer = new Answer();
        falseAnswer.setAnswer("False example answer");
        falseAnswer.setCorrect(false);
        Answer falseAnswer2 = new Answer();
        falseAnswer2.setAnswer("False example answer2");
        falseAnswer2.setCorrect(false);
        Answer falseAnswer3 = new Answer();
        falseAnswer3.setAnswer("False example answer3");
        falseAnswer3.setCorrect(false);
        Answer correctAnswer = new Answer();
        correctAnswer.setAnswer("Correct example answer");
        correctAnswer.setCorrect(true);


        Quiz quiz = new Quiz();
        quiz.setQuizName("Example Quiz 1");
        Question question1 = new Question();
        question1.setQuestion("Example Question 1");
        question1.setAnswerList(List.of(correctAnswer, falseAnswer, falseAnswer2));
        quiz.setQuestionList(List.of(question1));
        quizService.createQuiz(quiz);



/**
        Quiz quiz2 = new Quiz();
        quiz2.setQuizName("Example Quiz 2");
        Question question2 = new Question();
        question2.setQuestion("Example Question 2");
        question2.setAnswerList(List.of(falseAnswer, falseAnswer2, falseAnswer3, correctAnswer));
        quiz2.setQuestionList(List.of(question2));
        quizService.createQuiz(quiz2);
*/
    }
}
