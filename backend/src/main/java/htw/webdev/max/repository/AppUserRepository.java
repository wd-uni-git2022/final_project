package htw.webdev.max.repository;

import htw.webdev.max.model.AppUser;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppUserRepository implements PanacheRepository<AppUser> {

    public void addUser(AppUser appUser){
        appUser.setPassword(BcryptUtil.bcryptHash(appUser.getPassword()));
        appUser.role = "user";
        appUser.persist();
    }

}
