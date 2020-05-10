package reservation.backend.Entity;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationViewModel {

    @NotNull
    private String id;
    @NotNull
    private String lieuDepart;
    @NotNull
    private String lieuDestination;
    @NotNull
    private Date dateReservation;
    //0: Attente 1:Accepte 2:Refuse
    @NotNull
    private int etat;

    private List<EmployeViewModel> employes = new ArrayList<>();

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuDestination() {
        return lieuDestination;
    }

    public void setLieuDestination(String lieuDestination) {
        this.lieuDestination = lieuDestination;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public List<EmployeViewModel> getEmployes() {
        return employes;
    }

    public void setEmployes(List<EmployeViewModel> employes) {
        this.employes = employes;
    }
}
