package htw.webdev.max.controller;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.HashSet;

@Path("/api/auth")
public class AuthController {

    @POST
    @Path("login")
    public String login() {
        String token = Jwt.issuer("http://localhost/issuer")
                .upn("testuser@example.org")
                .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                .claim(Claims.preferred_username.name(), "testuser")
                .sign();

        return token;
    }

    @POST
    @Path("register")
    public String register() {
        return "register test";
    }
}
