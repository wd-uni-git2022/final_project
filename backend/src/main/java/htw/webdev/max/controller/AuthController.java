package htw.webdev.max.controller;

import htw.webdev.max.model.AppUser;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashSet;

@Path("/api/auth")
public class AuthController {

    private static final Logger LOG = Logger.getLogger(AuthController.class);


    @POST
    @Path("login")
    public String login() {
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
    public String register(AppUser appUser) {

        LOG.info("appUser: " + appUser.getUsername());

        return "register test";
    }
}
