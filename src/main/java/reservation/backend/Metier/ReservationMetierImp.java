package reservation.backend.Metier;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reservation.backend.Entity.*;
import reservation.backend.Repository.ReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationMetierImp implements ReservationMetier {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EmployeMetierImp employeMetierImp;

    @Override
    public ReservationViewModel findReservationById(String id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        if(reservation.isPresent()) {
            return convertToReservationViewModel(reservation.get());
        }
        else
            return null;
    }

    @Override
    public ReservationViewModel saveReservation(ReservationRepresentation reservationRepresentation) {
        Reservation reservation = new Reservation();
        reservation.setLieuDepart(reservationRepresentation.getLieuDepart());
        reservation.setLieuDestination(reservationRepresentation.getLieuDestination());
        reservation.setDateReservation(reservationRepresentation.getDateReservation());
        reservation.setEtat(0);

        List<Employe> employes = reservationRepresentation.getIdemployes().stream()
                .map(idemploye -> employeMetierImp.findEmployeById(idemploye))
                .collect(Collectors.toList());
        reservation.setEmployes(employes);

        Reservation reservationPersist = reservationRepository.save(reservation);
        return convertToReservationViewModel(reservationPersist);
    }


    @Override
    public List<ReservationViewModel> findAllReservation() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationViewModel> reservationViewModels = reservations.stream()
                .map(reservation -> convertToReservationViewModel(reservation))
                .collect(Collectors.toList());
        return reservationViewModels;
    }

    @Override
    public void deleteReservation(String id) throws ResourceNotFoundException {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found for this id : " + id));
        reservationRepository.deleteById(id);
    }


    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation reservationToUpdate = reservationRepository.findById(id).get();
        reservationToUpdate.setLieuDepart(reservation.getLieuDepart());
        reservationToUpdate.setLieuDestination(reservation.getLieuDestination());
        reservationToUpdate.setDateReservation(reservation.getDateReservation());
        reservationToUpdate.setEmployes(reservation.getEmployes());
        return reservationRepository.save(reservationToUpdate);

    }

    public ReservationViewModel convertToReservationViewModel(Reservation reservation){
        ReservationViewModel reservationViewModel = new ReservationViewModel();
        reservationViewModel.setLieuDepart(reservation.getLieuDepart());
        reservationViewModel.setLieuDestination(reservation.getLieuDestination());
        reservationViewModel.setDateReservation(reservation.getDateReservation());
        reservationViewModel.setEtat(reservation.getEtat());
        reservationViewModel.setId(reservation.getId());

        List<EmployeViewModel> employeViewModels = reservation.getEmployes().stream()
                .map(employe -> employeMetierImp.convertToEmployeViewModel(employe))
                .collect(Collectors.toList());

        reservationViewModel.setEmployes(employeViewModels);
        return reservationViewModel;
    }


}
