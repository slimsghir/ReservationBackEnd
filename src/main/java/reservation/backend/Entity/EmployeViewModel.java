package reservation.backend.Entity;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EmployeViewModel {

    @NotNull
    private String id;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String login;
    @NotNull
    private String email;
    @NotNull
    private Long phone;
    private EmployeViewModel superviseur;

    public EmployeViewModel getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(EmployeViewModel superviseur) {
        this.superviseur = superviseur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
