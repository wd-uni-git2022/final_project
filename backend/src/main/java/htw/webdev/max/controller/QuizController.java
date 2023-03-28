package htw.webdev.max.controller;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/api/quiz")
public class QuizController {

    @GET
    public String getQuizzes(){
        return "test response";
    }

    @POST
    @RolesAllowed({"User", "Admin"})
    public String addQuiz(){
        return "test response: added quiz";
    }
}
