package htw.webdev.max.service;

import htw.webdev.max.controller.AuthController;
import htw.webdev.max.model.AppUser;
import htw.webdev.max.repository.AppUserRepository;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@ApplicationScoped
public class AuthService {

    private static final Logger LOG = Logger.getLogger(AuthService.class);

    @Inject
    AppUserRepository appUserRepository;

    public boolean checkUserRegistrationData(AppUser appUser) {
        if (!appUser.getEmail().contains("@"))
            return false;

        if (appUser.getPassword().length() < 10)
            return false;

        return true;
    }

    @Transactional
    public void registerUser(AppUser appUser) {
        appUserRepository.addUser(appUser);

    }

    /**
     * Check if email is already registered
     * Duplicate usernames are allowed
     */
    public boolean doesUserExist(AppUser appUser) {
        long emailCount = AppUser.find("email", appUser.getEmail()).count();
        if (emailCount != 0)
            return true;
        return false;
    }

    public boolean validateUserCredentials(AppUser appUser) {
        Optional<AppUser> oDbAppUser = appUserRepository.find("email", appUser.getEmail()).stream().findFirst();

        if (oDbAppUser.isEmpty())
            return false;

        AppUser dbAppUser = oDbAppUser.get();
        if (BcryptUtil.matches(appUser.getPassword(), dbAppUser.getPassword())) {
            LOG.debug("Password matches hash");
            return true;
        } else {
            LOG.debug("Password doesn't match hash");
            return false;
        }
    }

    public AppUser getUserFromDb(String email) {
        return appUserRepository.find("email", email).stream().findFirst().get();
    }

    public String createToken(AppUser dbAppUser) {
        String token = Jwt.issuer("http://localhost/issuer")
                .upn(dbAppUser.getEmail())
                .groups(new HashSet<>(Collections.singletonList(dbAppUser.getRole())))
                .claim(Claims.preferred_username.name(), dbAppUser.getUsername())
                .expiresIn(Duration.ofHours(24))
                .sign();
        return token;
    }
}
