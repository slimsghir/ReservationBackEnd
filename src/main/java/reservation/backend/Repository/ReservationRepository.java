package reservation.backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reservation.backend.Entity.Reservation;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation,String> {
}
