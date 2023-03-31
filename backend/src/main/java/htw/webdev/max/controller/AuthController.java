package htw.webdev.max.controller;

import htw.webdev.max.model.AppUser;
import htw.webdev.max.service.AuthService;
import io.smallrye.jwt.build.Jwt;
import io.vertx.core.json.JsonObject;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Path("/api/auth")
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class);

    @Inject
    AuthService authService;

    @POST
    @Path("login")
    public Response login(AppUser appUser) {
        LOG.info("appUser login");
        LOG.info("appUser email: " + appUser.getEmail());
        LOG.debug("appUser password: " + appUser.getPassword());

        if (appUser.email == null || appUser.password == null) {
            LOG.info("Missing login data in login request");
            return Response.status(400).entity(JsonObject.of("reason", "data")).build();
        }

        if (!authService.validateUserCredentials(appUser)) {
            return Response.status(400).entity(JsonObject.of("reason", "credentials")).build();
        }

        AppUser dbAppUser = authService.getUserFromDb(appUser.email);

        String token = authService.createToken(dbAppUser);

        return Response.ok().entity(JsonObject.of("token", token)).build();
    }

    @POST
    @Path("register")
    public Response register(AppUser appUser) {
        LOG.info("appUser registration");
        LOG.info("appUser email: " + appUser.getEmail());
        LOG.info("appUser username: " + appUser.getUsername());
        LOG.debug("appUser password: " + appUser.getPassword());

        if (!authService.checkUserRegistrationData(appUser)) {
            LOG.info("Invalid registration data");
            return Response.status(400).entity(JsonObject.of("reason", "data")).build();
        }
        if (authService.doesUserExist(appUser)) {
            LOG.info("Email already exists");
            return Response.status(400).entity(JsonObject.of("reason", "email")).build();
        }

        authService.registerUser(appUser);

        String token = authService.createToken(appUser);

        return Response.ok().entity(JsonObject.of("token", token)).build();
    }
}
