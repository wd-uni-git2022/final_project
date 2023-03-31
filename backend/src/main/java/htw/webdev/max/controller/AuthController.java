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
import java.util.HashSet;

@Path("/api/auth")
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class);

    @Inject
    AuthService authService;

    @POST
    @Path("login")
    public String login(AppUser appUser) {

        String token = Jwt.issuer("http://localhost/issuer")
                .upn("testuser@example.org")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.preferred_username.name(), "testuser")
                .expiresIn(Duration.ofHours(24))
                .sign();

        return token;
    }

    @POST
    @Path("register")
    public Response register(AppUser appUser) {
        LOG.info("appUser registration");
        LOG.info("appUser email: " + appUser.getEmail());
        LOG.info("appUser username: " + appUser.getUsername());
        LOG.info("appUser password: " + appUser.getPassword());

        if(!authService.checkUserRegistrationData(appUser)){
            LOG.info("Invalid registration data");
            return Response.status(400, "Invalid registration data").build();
        }
        if(authService.doesUserExist(appUser)){
            LOG.info("Email already exists");
            return Response.status(400).entity(JsonObject.of("reason", "email")).build();
        }

        authService.registerUser(appUser);

        return Response.ok().build();
    }
}
