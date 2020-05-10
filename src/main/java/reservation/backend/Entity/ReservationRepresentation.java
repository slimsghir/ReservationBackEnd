package reservation.backend.Entity;


import java.util.Date;
import java.util.List;

public class ReservationRepresentation {

    private String lieuDepart;
    private String lieuDestination;
    private Date dateReservation;
    private List<String> idemployes;


    public List<String> getIdemployes() {
        return idemployes;
    }

    public void setIdemployes(List<String> idemployes) {
        this.idemployes = idemployes;
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

}
