package htw.webdev.max.service;

import htw.webdev.max.model.AppUser;
import htw.webdev.max.repository.AppUserRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    private static final Logger LOG = Logger.getLogger(UserService.class);


    @Inject
    AppUserRepository appUserRepository;

    public List<AppUser> getAllUsers() {
        List<AppUser> appUserList = appUserRepository.listAll();
        appUserList.forEach(u -> u.setPassword(""));
        return appUserList;
    }

    @Transactional
    public boolean deleteUser(long userId) {
        return appUserRepository.deleteById(userId);
    }

    @Transactional
    public boolean changeUsername(long userId, String username) {
        LOG.info("Change username for userId " + userId + " to " + username);
        Optional<AppUser> oAppUser = appUserRepository.findByIdOptional(userId);
        if (oAppUser.isEmpty())
            return false;
        AppUser appUser = oAppUser.get();
        appUser.setUsername(username);
        appUserRepository.persist(appUser);
        return true;
    }
}
