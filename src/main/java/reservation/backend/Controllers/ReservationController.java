package reservation.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reservation.backend.Entity.Reservation;
import reservation.backend.Entity.ReservationRepresentation;
import reservation.backend.Entity.ReservationViewModel;
import reservation.backend.Metier.ReservationMetier;
import reservation.backend.Metier.ResourceNotFoundException;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationMetier reservationMetierImp;

    @GetMapping("/findall")
    public List<ReservationViewModel> findAllReservation(){
        return reservationMetierImp.findAllReservation();
    }

    @RequestMapping(value = "/{id}")
    public ReservationViewModel findById(@PathVariable String id){
        return reservationMetierImp.findReservationById(id);
    }

    @PostMapping("/save")
        public ReservationViewModel saveReservation(@RequestBody ReservationRepresentation reservationRepresentation){
            return reservationMetierImp.saveReservation(reservationRepresentation);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) throws ResourceNotFoundException {
        reservationMetierImp.deleteReservation(id);
    }

    @PutMapping("/update/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        return reservationMetierImp.updateReservation(id,reservation);
    }

}
