package htw.webdev.max.controller;

import htw.webdev.max.model.Quiz;
import htw.webdev.max.service.QuizService;
import org.jboss.logging.Logger;


import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/api/quiz")
public class QuizController {

    private static final Logger LOG = Logger.getLogger(QuizController.class);

    @Inject
    QuizService quizService;

    @GET
    public Response getQuizzes() {

        List<Quiz> quizList = quizService.getAllQuizzes();
        return Response.ok(quizList).build();
    }

    @POST
    @RolesAllowed({"Admin"})
    public Response addQuiz(Quiz quiz) {

        quizService.saveQuiz(quiz);
        // LOG.info(jsonObject);
        LOG.info(quiz.toString());
        return Response.ok().build();
    }

    @DELETE
    @RolesAllowed({"Admin"})
    public Response deleteQuiz(Quiz quiz) {

        if (quizService.deleteQuiz(quiz)) {
            LOG.info("Deleted quiz with id " + quiz.getQuizId());
            return Response.ok().build();
        } else {
            LOG.info("Could not delete quiz with id " + quiz.getQuizId());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }
}
