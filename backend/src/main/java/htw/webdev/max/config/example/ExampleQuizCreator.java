package htw.webdev.max.config.example;

import htw.webdev.max.model.Answer;
import htw.webdev.max.model.Question;
import htw.webdev.max.model.Quiz;
import htw.webdev.max.service.QuizService;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Startup
@ApplicationScoped
public class ExampleQuizCreator {

    public ExampleQuizCreator(QuizService quizService){

        // Example quiz 1
        Quiz quiz = new Quiz();
        quiz.setQuizName("Example Quiz 1");

        // Question 1
        Answer falseAnswer = new Answer();
        falseAnswer.setAnswer("False example answer1");
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

        Question question1 = new Question();
        question1.setQuestion("Example Question 1");
        question1.setAnswerList(List.of(correctAnswer, falseAnswer, falseAnswer2, falseAnswer3));


        // Question 2
        Answer falseAnswer4 = new Answer();
        falseAnswer4.setAnswer("False example answer1");
        falseAnswer4.setCorrect(false);
        Answer falseAnswer5 = new Answer();
        falseAnswer5.setAnswer("False example answer2");
        falseAnswer5.setCorrect(false);
        Answer correctAnswer2 = new Answer();
        correctAnswer2.setAnswer("Correct example answer1");
        correctAnswer2.setCorrect(true);
        Answer correctAnswer3 = new Answer();
        correctAnswer3.setAnswer("Correct example answer2");
        correctAnswer3.setCorrect(true);
        Question question2 = new Question();
        question2.setQuestion("Example Question 2");
        question2.setAnswerList(List.of(falseAnswer4, falseAnswer5, correctAnswer2, correctAnswer3));

        quiz.setQuestionList(List.of(question1, question2));
        quizService.addQuiz(quiz);


    }
}
