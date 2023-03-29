package htw.webdev.max.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    private Long userId;

    private String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
