package cr.ac.ucr.ie.lenguajes_2025.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Daniel
 */
public class User {
 
    private int idUser;
    private String name;
     @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String email;
    private String password;
    private String urlProfilePicture;
    private String role;
    private boolean isActive;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    public User() {}

    public User(int idUser, String name, LocalDate birthdate, String email, String password, String urlProfilePicture, String role, boolean isActive, LocalDate creationDate) {
        this.idUser = idUser;
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        this.password = password;
        this.urlProfilePicture = urlProfilePicture;
        this.role = role;
        this.isActive = isActive;
        this.creationDate = creationDate;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlProfilePicture() {
        return urlProfilePicture;
    }

    public void setUrlProfilePicture(String urlProfilePicture) {
        this.urlProfilePicture = urlProfilePicture;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", name=" + name + ", birthdate=" + birthdate + ", email=" + email + ", password=" + password + ", urlProfilePicture=" + urlProfilePicture + ", role=" + role + ", isActive=" + isActive + ", creationDate=" + creationDate + '}';
    }
    
}
