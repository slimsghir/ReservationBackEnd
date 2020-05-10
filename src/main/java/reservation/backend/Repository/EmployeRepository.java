package reservation.backend.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import reservation.backend.Entity.Employe;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends MongoRepository<Employe,String> {
    public Optional<Employe> findEmployeByNom(String nom);
    public void deleteEmployeById(String id);
    public List<Employe> findAllBySuperviseur_Id(String idsuperviseur);
}
