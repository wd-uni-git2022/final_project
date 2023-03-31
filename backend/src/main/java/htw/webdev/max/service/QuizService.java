package htw.webdev.max.service;


import htw.webdev.max.model.Quiz;
import htw.webdev.max.repository.QuizRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class QuizService {

    @Inject
    QuizRepository quizRepository;

    public List<Quiz> getAllQuizzes(){
        return quizRepository.listAll();
    }

    @Transactional
    public void createQuiz(Quiz quiz) {

        quizRepository.persist(quiz);
    }

    public Quiz getQuizById(long quizId){
        return quizRepository.findById(quizId);
    }

    public boolean deleteQuiz(Quiz quiz) {
        if (quiz.getQuizId() == null)
            return false;

        return quizRepository.deleteById(quiz.getQuizId());
    }
}
