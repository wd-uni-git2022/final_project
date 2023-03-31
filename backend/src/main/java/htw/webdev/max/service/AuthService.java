package htw.webdev.max.service;

import htw.webdev.max.model.AppUser;
import htw.webdev.max.repository.AppUserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class AuthService {

    @Inject
    AppUserRepository appUserRepository;

    public boolean checkUserRegistrationData(AppUser appUser){
        if(!appUser.getEmail().contains("@"))
            return false;

        if(appUser.getPassword().length() < 10)
            return false;

        return true;
    }

    @Transactional
    public void registerUser(AppUser appUser){
        appUserRepository.addUser(appUser);

    }

    /**
     * Check if email is already registered
     * Duplicate usernames are allowed
     */
    public boolean doesUserExist(AppUser appUser){
        long emailCount = AppUser.find("email", appUser.getEmail()).count();
        if(emailCount != 0)
            return true;
        return false;
    }
}
