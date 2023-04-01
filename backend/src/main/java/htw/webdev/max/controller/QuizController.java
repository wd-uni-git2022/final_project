package htw.webdev.max.controller;

import htw.webdev.max.model.Quiz;
import htw.webdev.max.service.QuizService;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


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
        quizList.forEach(quiz -> quiz.setQuestionList(null));
        return Response.ok(quizList).build();
    }

    @GET
    @RolesAllowed({"User", "Admin"})
    @Path("/{quizId}")
    public Response getQuizById(@PathParam long quizId) {
        Quiz quiz = quizService.getQuizById(quizId);
        return Response.ok(quiz).build();
    }

    @POST
    @RolesAllowed("Admin")
    public Response addQuiz(Quiz quiz) {

        quizService.addQuiz(quiz);
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
