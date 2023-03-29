package htw.webdev.max.controller;

import htw.webdev.max.model.Quiz;
import org.jboss.logging.Logger;


import javax.annotation.security.RolesAllowed;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Path("/api/quiz")
public class QuizController {


    private static final Logger LOG = Logger.getLogger(QuizController.class);

    @GET
    public String getQuizzes() {
        return "test response";
    }

    @POST
    @RolesAllowed({"Admin"})
    public String addQuiz(Quiz quiz) {

        // LOG.info(jsonObject);
        LOG.info(quiz.toString());
        return "test response: added quiz";
    }

    @DELETE
    @RolesAllowed({"Admin"})
    public Response deleteQuiz(Quiz quiz) {

        if (quiz.getQuizId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        LOG.info("Delete quiz with id " + quiz.getQuizId());

        return Response.ok().build();
    }
}
