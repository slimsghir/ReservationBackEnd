package reservation.backend.Metier;

import reservation.backend.Entity.Reservation;
import reservation.backend.Entity.ReservationRepresentation;
import reservation.backend.Entity.ReservationViewModel;

import java.util.List;

public interface ReservationMetier {
    public ReservationViewModel findReservationById(String id);
    public ReservationViewModel saveReservation(ReservationRepresentation reservationRepresentation);
    public List<ReservationViewModel> findAllReservation();
    public void deleteReservation(String id) throws ResourceNotFoundException;
    public Reservation updateReservation(String id, Reservation reservation);
}
