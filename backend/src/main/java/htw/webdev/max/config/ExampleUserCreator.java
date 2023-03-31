package htw.webdev.max.config;

import htw.webdev.max.model.AppUser;
import htw.webdev.max.service.AuthService;
import io.quarkus.runtime.Startup;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Startup
@ApplicationScoped
public class ExampleUserCreator {




    public ExampleUserCreator(AuthService authService){
        AppUser exampleAdmin = new AppUser();
        exampleAdmin.setEmail("admin@example.org");
        exampleAdmin.setUsername("admin");
        exampleAdmin.setPassword("admin");
        authService.registerAdmin(exampleAdmin);

        AppUser exampleUser = new AppUser();
        exampleUser.setEmail("user@example.org");
        exampleUser.setUsername("user");
        exampleUser.setPassword("user");
        authService.registerUser(exampleUser);

    }
}
