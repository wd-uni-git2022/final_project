package htw.webdev.max.repository;

import htw.webdev.max.model.Quiz;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuizRepository implements PanacheRepository<Quiz> {

}
